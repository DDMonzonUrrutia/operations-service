package com.bootcamp.everis.operationsservice.command.services;

import com.bootcamp.everis.operationsservice.command.domain.services.PaymentCommandService;
import com.bootcamp.everis.operationsservice.query.projections.models.Payment;
import com.bootcamp.everis.operationsservice.query.projections.repository.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class PaymentCommandServiceImpl implements PaymentCommandService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Mono<Payment> create(Payment payment) {
        payment.setDate(LocalDateTime.now());
        return paymentRepository.save(payment);
    }

    @Override
    public Mono<Payment> update(String id, Payment request) {
        return paymentRepository.findById(id)
                .switchIfEmpty(Mono.error(new Exception("Payment Client Not founded")))
                .map(payment -> {
                    payment.setAmount(request.getAmount());
                    payment.setCredit(request.getCredit());
                    return payment;
                })
                .flatMap(paymentRepository::save);
    }

    @Override
    public Mono<Void> delete(String id) {
        return paymentRepository.findById(id)
                .switchIfEmpty(Mono.error(new Exception("Payment Not Founded")))
                .flatMap(paymentRepository::delete);
    }
}
