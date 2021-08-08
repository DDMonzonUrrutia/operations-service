package com.bootcamp.everis.operationsservice.query.projections.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BaseQueryService<T> {
    Flux<T> findAll();
    Mono<T> findById(String id);

}
