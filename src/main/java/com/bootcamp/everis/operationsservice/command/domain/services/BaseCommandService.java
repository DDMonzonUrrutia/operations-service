package com.bootcamp.everis.operationsservice.command.domain.services;

import reactor.core.publisher.Mono;

public interface BaseCommandService<T> {
    Mono<T> create(T entity);
    Mono<T> update(String id, T entity);
    Mono<Void> delete(String id);
}
