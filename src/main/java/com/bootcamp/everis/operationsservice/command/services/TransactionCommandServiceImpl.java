package com.bootcamp.everis.operationsservice.command.services;

import com.bootcamp.everis.operationsservice.command.domain.services.TransactionCommandService;
import com.bootcamp.everis.operationsservice.query.projections.models.Transaction;
import com.bootcamp.everis.operationsservice.query.projections.repository.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class TransactionCommandServiceImpl implements TransactionCommandService {

    @Autowired
    private WebClient.Builder builder;

    @Autowired
    private TransactionRepository transactionRepository;


    @Override
    public Mono<Transaction> create(Transaction transaction) {
        transaction.setDate(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }

    @Override
    public Mono<Transaction> update(String id, Transaction request) {
        return transactionRepository.findById(id)
                .map(transaction -> {
                    transaction.setToAccount(request.getToAccount());
                    transaction.setFromAccount(request.getFromAccount());
                    transaction.setAmount(request.getAmount());
                    return transaction;
                })
                .flatMap(transactionRepository::save)
                .switchIfEmpty(Mono.error(new Exception("Transaction not founded")));
    }

    @Override
    public Mono<Void> delete(String id) {
        return transactionRepository.findById(id)
                .switchIfEmpty(Mono.error(new Exception("Transaction Client Not Founded")))
                .flatMap(transactionRepository::delete);
    }
}
