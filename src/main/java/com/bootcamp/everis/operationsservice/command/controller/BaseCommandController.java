package com.bootcamp.everis.operationsservice.command.controller;

import reactor.core.publisher.Mono;

public interface BaseCommandController<T, R> {
    Mono<R> create(T request);
    Mono<R> update(String id, T request);
    Mono<?> delete(String id);
}
