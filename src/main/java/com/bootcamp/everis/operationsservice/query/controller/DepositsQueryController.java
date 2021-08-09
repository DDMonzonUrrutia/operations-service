package com.bootcamp.everis.operationsservice.query.controller;

import com.bootcamp.everis.operationsservice.query.projections.services.DepositQueryService;
import com.bootcamp.everis.operationsservice.shared.dto.DepositResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/operations")
public class DepositsQueryController implements BaseQueryController<DepositResponseDto>{

    @Autowired
    private DepositQueryService depositQueryService;

    @GetMapping("/deposits")
    @Override
    public Flux<DepositResponseDto> getAll() {
        return depositQueryService.findAll().map(DepositResponseDto::entityToResponse);
    }

    @GetMapping("/deposits/{depositId}")
    @Override
    public Mono<DepositResponseDto> getById(@PathVariable(name = "depositId") String id) {
        return depositQueryService.findById(id).map(DepositResponseDto::entityToResponse).switchIfEmpty(Mono.error(new Exception("Purchase not founded")));
    }
}
