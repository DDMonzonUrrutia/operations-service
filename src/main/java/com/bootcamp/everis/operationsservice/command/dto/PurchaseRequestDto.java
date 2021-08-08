package com.bootcamp.everis.operationsservice.command.dto;

import com.bootcamp.everis.operationsservice.query.projections.models.Purchase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PurchaseRequestDto extends OperationRequestDto{
    private String creditCard;
    private String business;

    static public Purchase requestToEntity(PurchaseRequestDto requestDto) {
        Purchase purchase = new Purchase();
        purchase.setAmount(requestDto.getAmount());
        purchase.setBusiness(requestDto.getBusiness());
        purchase.setCreditCard(requestDto.getCreditCard());
        return purchase;

    }
}
