package com.bootcamp.everis.operationsservice.query.controller;

import com.bootcamp.everis.operationsservice.query.projections.services.PaymentQueryService;
import com.bootcamp.everis.operationsservice.shared.dto.PaymentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/operations")
public class PaymentsQueryController implements BaseQueryController<PaymentResponseDto>{

    @Autowired
    private PaymentQueryService paymentQueryService;

    @Override
    public Flux<PaymentResponseDto> getAll() {
        return paymentQueryService.findAll().map(PaymentResponseDto::entityToResource);
    }

    @GetMapping("/payments/{paymentId}")
    @Override
    public Mono<PaymentResponseDto> getById(@PathVariable(name = "paymentId") String id) {
        return paymentQueryService.findById(id).map(PaymentResponseDto::entityToResource).switchIfEmpty(Mono.error(new Exception("Payment not founded")));
    }
}
