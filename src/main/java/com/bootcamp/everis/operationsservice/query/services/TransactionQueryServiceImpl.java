package com.bootcamp.everis.operationsservice.query.services;

import com.bootcamp.everis.operationsservice.query.projections.models.Transaction;
import com.bootcamp.everis.operationsservice.query.projections.repository.repositories.TransactionRepository;
import com.bootcamp.everis.operationsservice.query.projections.services.TransactionQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j(topic = "TRANSACTION_QUERY_SERVICE")
public class TransactionQueryServiceImpl implements TransactionQueryService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Flux<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Mono<Transaction> findById(String id) {
        return transactionRepository.findById(id).switchIfEmpty(Mono.error(new Exception("Transaction not founded")));
    }

}
