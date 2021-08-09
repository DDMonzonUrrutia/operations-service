package com.bootcamp.everis.operationsservice.command.services;

import com.bootcamp.everis.operationsservice.command.domain.services.DepositCommandService;
import com.bootcamp.everis.operationsservice.query.projections.models.Deposit;
import com.bootcamp.everis.operationsservice.query.projections.repository.repositories.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class DepositCommandServiceImpl implements DepositCommandService {

    @Autowired
    private WebClient.Builder builder;

    @Autowired
    private DepositRepository depositRepository;


    @Override
    public Mono<Deposit> create(Deposit deposit) {
        deposit.setDate(LocalDateTime.now());
        return depositRepository.save(deposit);
    }

    @Override
    public Mono<Deposit> update(String id, Deposit request) {
        return depositRepository.findById(id)
                .map(transaction -> {
                    transaction.setToAccount(request.getToAccount());
                    transaction.setAmount(request.getAmount());
                    return transaction;
                })
                .flatMap(depositRepository::save)
                .switchIfEmpty(Mono.error(new Exception("Deposit not founded")));
    }

    @Override
    public Mono<Void> delete(String id) {
        return depositRepository.findById(id)
                .switchIfEmpty(Mono.error(new Exception("Deposit Not Founded")))
                .flatMap(depositRepository::delete);
    }
}
