package com.bootcamp.everis.operationsservice.command.controller;

import com.bootcamp.everis.operationsservice.command.domain.services.PaymentCommandService;
import com.bootcamp.everis.operationsservice.command.dto.PaymentRequestDto;
import com.bootcamp.everis.operationsservice.query.projections.models.Payment;
import com.bootcamp.everis.operationsservice.shared.contracts.CreditProductDto;
import com.bootcamp.everis.operationsservice.shared.dto.PaymentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/operations")
public class PaymentsCommandController implements BaseCommandController<PaymentRequestDto, PaymentResponseDto>{

    @Autowired
    private WebClient.Builder builder;

    @Autowired
    private PaymentCommandService paymentCommandService;

    @PostMapping("/payments")
    @Override
    public Mono<PaymentResponseDto> create(
            @RequestBody PaymentRequestDto request) {
        Payment payment = PaymentRequestDto.requestToEntity(request);
        return builder.build()
                .get().uri("http://localhost:8084/product-credits/" + request.getCredit())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> {
                    return Mono.error(new Exception("error"));
                }).bodyToMono(CreditProductDto.class)
                .flatMap(creditProductDto ->  paymentCommandService.create(payment)
                        .switchIfEmpty(Mono.error(new Exception("Error while create the payment ")))
                        .map(PaymentResponseDto::entityToResource))
                .onErrorResume(e -> Mono.error(new Exception("Error on create payment")));
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
