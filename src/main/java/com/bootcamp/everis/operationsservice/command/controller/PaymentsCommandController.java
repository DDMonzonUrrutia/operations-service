package com.bootcamp.everis.operationsservice.command.controller;

import com.bootcamp.everis.operationsservice.command.domain.services.PaymentCommandService;
import com.bootcamp.everis.operationsservice.command.dto.PaymentRequestDto;
import com.bootcamp.everis.operationsservice.query.projections.models.Payment;
import com.bootcamp.everis.operationsservice.shared.dto.PaymentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/operations")
public class PaymentsCommandController implements BaseCommandController<PaymentRequestDto, PaymentResponseDto>{

    @Autowired
    private PaymentCommandService paymentCommandService;

    @PostMapping("/payments")
    @Override
    public Mono<PaymentResponseDto> create(
            @RequestBody PaymentRequestDto request) {
        Payment payment = PaymentRequestDto.requestToEntity(request);
        return paymentCommandService.create(payment)
                .switchIfEmpty(Mono.error(new Exception("Error while create the payment ")))
                .map(PaymentResponseDto::entityToResource);
    }

    @PutMapping("/payments")
    @Override
    public Mono<PaymentResponseDto> update(
            @RequestParam(name = "paymentId") String id,
            @RequestBody PaymentRequestDto request) {
        Payment payment = PaymentRequestDto.requestToEntity(request);
        return paymentCommandService.update(id, payment)
                .switchIfEmpty(Mono.error(new Exception("Error while update the payment")))
                .map(PaymentResponseDto::entityToResource);
    }

    @DeleteMapping("/payments")
    @Override
    public Mono<?> delete(@RequestParam(name = "paymentId") String id) {
        return paymentCommandService.delete(id);
    }
}
