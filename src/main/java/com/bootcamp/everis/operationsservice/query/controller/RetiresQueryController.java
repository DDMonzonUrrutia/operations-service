package com.bootcamp.everis.operationsservice.query.controller;

import com.bootcamp.everis.operationsservice.query.projections.services.RetireQueryService;
import com.bootcamp.everis.operationsservice.shared.dto.RetireResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/operations")
public class RetiresQueryController implements BaseQueryController<RetireResponseDto>{
    @Autowired
    private RetireQueryService retireQueryService;

    @GetMapping("/retires")
    @Override
    public Flux<RetireResponseDto> getAll() {
        return retireQueryService.findAll()
                .map(RetireResponseDto::entityToResponse);
    }

    @GetMapping("/retires/{retireId}")
    @Override
    public Mono<RetireResponseDto> getById(@PathVariable("retireId") String retireId) {
        return retireQueryService.findById(retireId)
                .map(RetireResponseDto::entityToResponse)
                .onErrorResume(error -> Mono.error(new Exception("Retire not founded")));
    }
}
