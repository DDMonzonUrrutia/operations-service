package com.bootcamp.everis.operationsservice.query.services;

import com.bootcamp.everis.operationsservice.query.projections.models.Deposit;
import com.bootcamp.everis.operationsservice.query.projections.repository.repositories.DepositRepository;
import com.bootcamp.everis.operationsservice.query.projections.services.DepositQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j(topic = "TRANSACTION_QUERY_SERVICE")
public class DepositQueryServiceImpl implements DepositQueryService {

    @Autowired
    private DepositRepository depositRepository;

    @Override
    public Flux<Deposit> findAll() {
        return depositRepository.findAll();
    }

    @Override
    public Mono<Deposit> findById(String id) {
        return depositRepository.findById(id).switchIfEmpty(Mono.error(new Exception("Deposit not founded")));
    }

}
