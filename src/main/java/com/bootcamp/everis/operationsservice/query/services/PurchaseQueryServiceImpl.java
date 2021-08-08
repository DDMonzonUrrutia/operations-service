package com.bootcamp.everis.operationsservice.query.services;

import com.bootcamp.everis.operationsservice.query.projections.models.Purchase;
import com.bootcamp.everis.operationsservice.query.projections.repository.repositories.PurchaseRepository;
import com.bootcamp.everis.operationsservice.query.projections.services.PurchaseQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j(topic = "PURCHASE_QUERY_SERVICE")
public class PurchaseQueryServiceImpl implements PurchaseQueryService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public Flux<Purchase> findAll() {
        return purchaseRepository.findAll();
    }

    @Override
    public Mono<Purchase> findById(String id) {
        return purchaseRepository.findById(id).switchIfEmpty(Mono.error(new Exception("Purchase not founded")));
    }


}
