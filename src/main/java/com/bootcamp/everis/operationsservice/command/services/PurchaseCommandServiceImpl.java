package com.bootcamp.everis.operationsservice.command.services;

import com.bootcamp.everis.operationsservice.command.domain.services.PurchaseCommandService;
import com.bootcamp.everis.operationsservice.query.projections.models.Purchase;
import com.bootcamp.everis.operationsservice.query.projections.repository.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class PurchaseCommandServiceImpl implements PurchaseCommandService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public Mono<Purchase> create(Purchase purchase) {
        purchase.setDate(LocalDateTime.now());
        return purchaseRepository.save(purchase);
    }

    @Override
    public Mono<Purchase> update(String id, Purchase request) {
        return purchaseRepository.findById(id)
                .switchIfEmpty(Mono.error(new Exception("Purchase Not founded")))
                .map(purchase -> {
                    purchase.setAmount(request.getAmount());
                    purchase.setCreditCard(request.getCreditCard());
                    purchase.setBusiness(request.getBusiness());
                    return purchase;
                })
                .flatMap(purchaseRepository::save);
    }

    @Override
    public Mono<Void> delete(String id) {
        return purchaseRepository.findById(id)
                .switchIfEmpty(Mono.error(new Exception("Purchase not founded")))
                .flatMap(purchaseRepository::delete);
    }
}
