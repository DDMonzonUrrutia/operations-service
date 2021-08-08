package com.bootcamp.everis.operationsservice.query.projections.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BaseRepository<R, T> extends ReactiveMongoRepository<R, T> {
}
