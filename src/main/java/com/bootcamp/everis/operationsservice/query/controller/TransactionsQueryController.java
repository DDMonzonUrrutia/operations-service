package com.bootcamp.everis.operationsservice.query.controller;

import com.bootcamp.everis.operationsservice.query.projections.services.TransactionQueryService;
import com.bootcamp.everis.operationsservice.shared.dto.TransactionResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/operations")
public class TransactionsQueryController implements BaseQueryController<TransactionResponseDto>{

    @Autowired
    private TransactionQueryService transactionQueryService;

    @GetMapping("/transactions")
    @Override
    public Flux<TransactionResponseDto> getAll() {
        return transactionQueryService.findAll().map(TransactionResponseDto::entityToResponse);
    }

    @GetMapping("/transactions/{transactionId}")
    @Override
    public Mono<TransactionResponseDto> getById(@PathVariable(name = "transactionId") String id) {
        return transactionQueryService.findById(id).map(TransactionResponseDto::entityToResponse).switchIfEmpty(Mono.error(new Exception("Purchase not founded")));
    }
}
