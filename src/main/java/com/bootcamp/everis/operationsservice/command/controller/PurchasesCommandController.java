package com.bootcamp.everis.operationsservice.command.controller;

import com.bootcamp.everis.operationsservice.command.domain.services.PurchaseCommandService;
import com.bootcamp.everis.operationsservice.command.dto.PurchaseRequestDto;
import com.bootcamp.everis.operationsservice.query.projections.models.Purchase;
import com.bootcamp.everis.operationsservice.shared.contracts.AccountBankDto;
import com.bootcamp.everis.operationsservice.shared.contracts.CreditProductDto;
import com.bootcamp.everis.operationsservice.shared.dto.PurchaseResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/operations")
public class PurchasesCommandController implements BaseCommandController<PurchaseRequestDto, PurchaseResponseDto>{

    @Autowired
    private WebClient.Builder builder;

    @Autowired
    private PurchaseCommandService purchaseCommandService;

    @PostMapping("/purchases")
    @Override
    public Mono<PurchaseResponseDto> create(@RequestBody PurchaseRequestDto request) {
        Purchase purchase = PurchaseRequestDto.requestToEntity(request);
        return builder.build()
                .get().uri("http://localhost:8084/product-credits/" + request.getCreditCard())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> {
                    return Mono.error(new Exception("error"));
                }).bodyToMono(CreditProductDto.class)
                .flatMap(creditProductDto ->  purchaseCommandService.create(purchase)
                        .switchIfEmpty(Mono.error(new Exception("Error while create the purchase")))
                        .map(PurchaseResponseDto::entityToResponse))
                .onErrorResume(e -> Mono.error(new Exception("Error on create purchase")));
    }

    @PutMapping("/purchases")
    @Override
    public Mono<PurchaseResponseDto> update(@RequestParam(name = "purchaseId") String id, @RequestBody PurchaseRequestDto request) {
        Purchase purchase = PurchaseRequestDto.requestToEntity(request);
        return purchaseCommandService.update(id, purchase)
                .map(PurchaseResponseDto::entityToResponse);
    }

    @DeleteMapping("/purchases")
    @Override
    public Mono<?> delete(@RequestParam(name = "purchaseId") String id) {
        return purchaseCommandService.delete(id);
    }
}
