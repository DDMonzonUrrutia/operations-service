package com.bootcamp.everis.operationsservice.command.controller;

import com.bootcamp.everis.operationsservice.command.domain.services.TransactionCommandService;
import com.bootcamp.everis.operationsservice.command.dto.TransactionRequestDto;
import com.bootcamp.everis.operationsservice.query.projections.models.Transaction;
import com.bootcamp.everis.operationsservice.shared.contracts.PersonalResponseDto;
import com.bootcamp.everis.operationsservice.shared.dto.TransactionResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/operations")
public class TransactionsCommandController implements BaseCommandController<TransactionRequestDto, TransactionResponseDto> {

    @Autowired
    private WebClient.Builder builder;

    @Autowired
    private TransactionCommandService transactionCommandService;

    @PostMapping("/transactions")
    @Override
    public Mono<TransactionResponseDto> create(@RequestBody TransactionRequestDto request) {
        Transaction transaction = TransactionRequestDto.requestToEntity(request);
        return  builder.build()
                .get().uri("http://localhost:8081/clients/personals/"+request.getFromAccount())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> {
                    return Mono.error(new Exception("error"));
                }).bodyToMono(PersonalResponseDto.class)
                .flatMap(personalResponseDto ->
                        builder.build()
                                .get().uri("http://localhost:8081/clients/personals/"+request.getToAccount())
                                .accept(MediaType.APPLICATION_JSON)
                                .retrieve()
                                .onStatus(HttpStatus::isError, clientResponse -> {
                                    return Mono.error(new Exception("error"));
                                }).bodyToMono(PersonalResponseDto.class))
                .flatMap(personalResponseDto -> transactionCommandService.create(transaction)
                        .switchIfEmpty(Mono.error(new Exception("Error while create the transaction ")))
                        .map(TransactionResponseDto::entityToResponse))
                .onErrorResume(e -> Mono.error(new Exception("Error on create transaction")));
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
