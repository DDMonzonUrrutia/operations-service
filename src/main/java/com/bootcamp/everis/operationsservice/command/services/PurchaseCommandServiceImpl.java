package com.bootcamp.everis.operationsservice.command.services;

import com.bootcamp.everis.operationsservice.command.domain.services.PurchaseCommandService;
import com.bootcamp.everis.operationsservice.query.projections.models.Purchase;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PurchaseCommandServiceImpl implements PurchaseCommandService {

    @Override
    public Mono<Purchase> create(Purchase entity) {
        return null;
    }

    @Override
    public Mono<Purchase> update(String id, Purchase entity) {
        return null;
    }

    @Override
    public Mono<Void> delete(String id) {
        return null;
    }
}
