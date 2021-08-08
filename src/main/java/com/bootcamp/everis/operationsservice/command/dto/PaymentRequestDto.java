package com.bootcamp.everis.operationsservice.command.dto;

import com.bootcamp.everis.operationsservice.query.projections.models.Payment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentRequestDto extends OperationRequestDto{
    private String credit;
    private String creditType;

    static public Payment requestToDto(PaymentRequestDto requestDto) {
        Payment payment = new Payment();
        payment.setAmount(requestDto.getAmount());
        payment.setCredit(requestDto.getCredit());
        payment.setCreditType(requestDto.getCreditType());
        return payment;
    }
}
