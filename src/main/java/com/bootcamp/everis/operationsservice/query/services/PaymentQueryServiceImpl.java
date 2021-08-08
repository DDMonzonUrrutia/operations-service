package com.bootcamp.everis.operationsservice.query.services;

import com.bootcamp.everis.operationsservice.query.projections.models.Payment;
import com.bootcamp.everis.operationsservice.query.projections.repository.repositories.PaymentRepository;
import com.bootcamp.everis.operationsservice.query.projections.services.PaymentQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j(topic = "PAYMENT_QUERY_SERVICE")
public class PaymentQueryServiceImpl implements PaymentQueryService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Flux<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Mono<Payment> findById(String id) {
        return paymentRepository.findById(id).switchIfEmpty(Mono.error(new Exception("Payment not founded")));
    }

}
