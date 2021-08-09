package com.bootcamp.everis.operationsservice.query.services;

import com.bootcamp.everis.operationsservice.query.projections.models.Retire;
import com.bootcamp.everis.operationsservice.query.projections.repository.repositories.RetireRepository;
import com.bootcamp.everis.operationsservice.query.projections.services.RetireQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RetireQueryServiceImpl implements RetireQueryService {

    @Autowired
    private RetireRepository retireRepository;

    @Override
    public Flux<Retire> findAll() {
        return retireRepository.findAll();
    }

    @Override
    public Mono<Retire> findById(String id) {
        return retireRepository.findById(id);
    }
}
