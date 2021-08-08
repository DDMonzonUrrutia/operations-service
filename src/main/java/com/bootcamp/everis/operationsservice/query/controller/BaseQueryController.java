package com.bootcamp.everis.operationsservice.query.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface BaseQueryController<T> {
    public Flux<T> getAll();
    public Mono<T> getById(String id);
}
