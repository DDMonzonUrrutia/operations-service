package com.bootcamp.everis.operationsservice.command.services;

import com.bootcamp.everis.operationsservice.command.domain.services.PaymentCommandService;
import com.bootcamp.everis.operationsservice.query.projections.models.Payment;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PaymentCommandServiceImpl implements PaymentCommandService {

    @Override
    public Mono<Payment> create(Payment entity) {
        return null;
    }

    @Override
    public Mono<Payment> update(String id, Payment entity) {
        return null;
    }

    @Override
    public Mono<Void> delete(String id) {
        return null;
    }
}
