package com.bootcamp.everis.operationsservice.command.controller;

import com.bootcamp.everis.operationsservice.command.domain.services.DepositCommandService;
import com.bootcamp.everis.operationsservice.command.dto.DepositRequestDto;
import com.bootcamp.everis.operationsservice.query.projections.models.Deposit;
import com.bootcamp.everis.operationsservice.shared.contracts.AccountBankDto;
import com.bootcamp.everis.operationsservice.shared.dto.DepositResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/operations")
public class DepositCommandController implements BaseCommandController<DepositRequestDto, DepositResponseDto> {

    @Autowired
    private WebClient.Builder builder;

    @Autowired
    private DepositCommandService depositCommandService;

    @PostMapping("/deposits")
    @Override
    public Mono<DepositResponseDto> create(@RequestBody DepositRequestDto request) {
        Deposit deposit = DepositRequestDto.requestToEntity(request);
        return builder.build()
                .get().uri("http://localhost:8085/account-banks/" + request.getToAccount())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> {
                    return Mono.error(new Exception("error"));
                }).bodyToMono(AccountBankDto.class)
                .flatMap(personalResponseDto -> depositCommandService.create(deposit)
                        .switchIfEmpty(Mono.error(new Exception("Error while create the deposit ")))
                        .map(DepositResponseDto::entityToResponse))
                .onErrorResume(e -> Mono.error(new Exception("Error on create deposit")));
    }

    @PutMapping("/deposits")
    @Override
    public Mono<DepositResponseDto> update(
            @RequestParam(name = "depositId") String id,
            @RequestBody DepositRequestDto request) {
        Deposit deposit = DepositRequestDto.requestToEntity(request);
        return depositCommandService.update(id, deposit)
                .switchIfEmpty(Mono.error(new Exception("Error while update the deposit")))
                .map(DepositResponseDto::entityToResponse);
    }

    @DeleteMapping("/deposits")
    @Override
    public Mono<?> delete(
            @RequestParam(name = "depositId") String id) {
        return depositCommandService.delete(id);
    }
}
