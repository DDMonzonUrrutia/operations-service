package com.bootcamp.everis.operationsservice.shared.dto;

import com.bootcamp.everis.operationsservice.query.projections.models.Purchase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PurchaseResponseDto extends OperationResponseDto{
    private String creditCard;
    private String business;

    static public PurchaseResponseDto entityToResponse(Purchase purchase) {
        PurchaseResponseDto responseDto = new PurchaseResponseDto();
        responseDto.setId(purchase.getId());
        responseDto.setAmount(purchase.getAmount());
        responseDto.setDate(purchase.getDate());
        responseDto.setBusiness(purchase.getBusiness());
        responseDto.setCreditCard(purchase.getCreditCard());
        return responseDto;
    }
}
