package com.bootcamp.everis.operationsservice.command.services;

import com.bootcamp.everis.operationsservice.command.domain.services.RetireCommandService;
import com.bootcamp.everis.operationsservice.query.projections.models.Retire;
import com.bootcamp.everis.operationsservice.query.projections.repository.repositories.RetireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class RetireCommandServiceImpl implements RetireCommandService {
    @Autowired
    private RetireRepository retireRepository;

    @Override
    public Mono<Retire> create(Retire retire) {
        retire.setDate(LocalDateTime.now());
        return retireRepository.save(retire);
    }

    @Override
    public Mono<Retire> update(String id, Retire request) {
        return retireRepository.findById(id)
                .map(retire -> {
                    retire.setFromAccount(request.getFromAccount());
                    retire.setAmount(request.getAmount());
                    return retire;
                })
                .flatMap(retireRepository::save)
                .switchIfEmpty(Mono.error(new Exception("Retire not founded")));
    }

    @Override
    public Mono<Void> delete(String id) {
        return retireRepository.findById(id)
                .switchIfEmpty(Mono.error(new Exception("Retire Not Founded")))
                .flatMap(retireRepository::delete);
    }
}
