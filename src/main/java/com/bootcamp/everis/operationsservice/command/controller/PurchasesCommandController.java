package com.bootcamp.everis.operationsservice.command.controller;

import com.bootcamp.everis.operationsservice.command.domain.services.PurchaseCommandService;
import com.bootcamp.everis.operationsservice.command.dto.PurchaseRequestDto;
import com.bootcamp.everis.operationsservice.query.projections.models.Purchase;
import com.bootcamp.everis.operationsservice.shared.dto.PurchaseResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/operations")
public class PurchasesCommandController implements BaseCommandController<PurchaseRequestDto, PurchaseResponseDto>{

    @Autowired
    private PurchaseCommandService purchaseCommandService;

    @PostMapping("/purchases")
    @Override
    public Mono<PurchaseResponseDto> create(@RequestBody PurchaseRequestDto request) {
        Purchase purchase = PurchaseRequestDto.requestToEntity(request);
        return purchaseCommandService.create(purchase)
                .switchIfEmpty(Mono.error(new Exception("Error while create the purchase")))
                .map(PurchaseResponseDto::entityToResponse);
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
