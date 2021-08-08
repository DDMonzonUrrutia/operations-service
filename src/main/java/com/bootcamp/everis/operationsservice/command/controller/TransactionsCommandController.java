package com.bootcamp.everis.operationsservice.command.controller;

import com.bootcamp.everis.operationsservice.command.domain.services.TransactionCommandService;
import com.bootcamp.everis.operationsservice.command.dto.TransactionRequestDto;
import com.bootcamp.everis.operationsservice.query.projections.models.Transaction;
import com.bootcamp.everis.operationsservice.shared.dto.TransactionResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/operations")
public class TransactionsCommandController implements BaseCommandController<TransactionRequestDto, TransactionResponseDto> {

    @Autowired
    private TransactionCommandService transactionCommandService;

    @PostMapping("/transactions")
    @Override
    public Mono<TransactionResponseDto> create(@RequestBody TransactionRequestDto request) {
        Transaction transaction = TransactionRequestDto.requestToEntity(request);
        return transactionCommandService.create(transaction)
                .switchIfEmpty(Mono.error(new Exception("Error while create the transaction ")))
                .map(TransactionResponseDto::entityToResponse);
    }

    @PutMapping("/transactions")
    @Override
    public Mono<TransactionResponseDto> update(
            @RequestParam(name = "transactionId") String id,
            @RequestBody TransactionRequestDto request) {
        Transaction transaction = TransactionRequestDto.requestToEntity(request);
        return transactionCommandService.update(id, transaction)
                .switchIfEmpty(Mono.error(new Exception("Error while update the transaction")))
                .map(TransactionResponseDto::entityToResponse);
    }

    @DeleteMapping("/transactions")
    @Override
    public Mono<?> delete(
            @RequestParam(name = "transactionId") String id) {
        return transactionCommandService.delete(id);
    }
}
