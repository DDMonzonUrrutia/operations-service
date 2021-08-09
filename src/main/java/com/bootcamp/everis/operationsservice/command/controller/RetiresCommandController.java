package com.bootcamp.everis.operationsservice.command.controller;

import com.bootcamp.everis.operationsservice.command.domain.services.RetireCommandService;
import com.bootcamp.everis.operationsservice.command.dto.RetireRequestDto;
import com.bootcamp.everis.operationsservice.query.projections.models.Retire;
import com.bootcamp.everis.operationsservice.shared.contracts.AccountBankDto;
import com.bootcamp.everis.operationsservice.shared.dto.RetireResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/operations")
public class RetiresCommandController implements BaseCommandController<RetireRequestDto, RetireResponseDto> {

    @Autowired
    private WebClient.Builder builder;

    @Autowired
    private RetireCommandService retireCommandService;

    @PostMapping("/retires")
    @Override
    public Mono<RetireResponseDto> create(@RequestBody RetireRequestDto request) {
        Retire retire = RetireRequestDto.requestToEntity(request);
        return builder.build()
                .get().uri("http://localhost:8085/account-banks/"+request.getFromAccount())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> {
                    return Mono.error(new Exception("error"));
                }).bodyToMono(AccountBankDto.class)
                .flatMap(personalResponseDto -> retireCommandService.create(retire)
                        .switchIfEmpty(Mono.error(new Exception("Error while create the retire ")))
                        .map(RetireResponseDto::entityToResponse))
                .onErrorResume(e -> Mono.error(new Exception("Error on create retire")));
    }

    @PutMapping("/retires")
    @Override
    public Mono<RetireResponseDto> update(@RequestParam(name = "retireId") String id, @RequestBody RetireRequestDto request) {
        Retire retire = RetireRequestDto.requestToEntity(request);
        return retireCommandService.update(id, retire)
                .switchIfEmpty(Mono.error(new Exception("Error while update the retire")))
                .map(RetireResponseDto::entityToResponse);
    }

    @DeleteMapping("/retires")
    @Override
    public Mono<?> delete(@RequestParam(name = "retireId") String id) {
        return retireCommandService.delete(id);
    }
}
