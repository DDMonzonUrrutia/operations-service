package com.bootcamp.everis.operationsservice.shared.dto;

import com.bootcamp.everis.operationsservice.query.projections.models.Payment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentResponseDto extends OperationResponseDto{
    private String credit;
    private String creditType;

    static public PaymentResponseDto entityToResource(Payment payment) {
        PaymentResponseDto responseDto = new PaymentResponseDto();
        responseDto.setId(payment.getId());
        responseDto.setDate(payment.getDate());
        responseDto.setAmount(payment.getAmount());
        responseDto.setCredit(payment.getCredit());
        responseDto.setCreditType(responseDto.getCreditType());
        return responseDto;
    }
}
