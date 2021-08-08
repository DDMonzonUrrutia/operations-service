package com.bootcamp.everis.operationsservice.query.controller;

import com.bootcamp.everis.operationsservice.query.projections.services.PurchaseQueryService;
import com.bootcamp.everis.operationsservice.shared.dto.PurchaseResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/operations")
public class PurchasesQueryController implements BaseQueryController<PurchaseResponseDto>{

    @Autowired
    private PurchaseQueryService purchaseQueryService;

    @GetMapping("/purchases")
    @Override
    public Flux<PurchaseResponseDto> getAll() {
        return purchaseQueryService.findAll().map(PurchaseResponseDto::entityToResponse);
    }

    @GetMapping("/purchases/{purchaseId}")
    @Override
    public Mono<PurchaseResponseDto> getById(@PathVariable(name = "purchaseId") String id) {
        return purchaseQueryService.findById(id).map(PurchaseResponseDto::entityToResponse).switchIfEmpty(Mono.error(new Exception("Purchase not founded")));
    }
}
