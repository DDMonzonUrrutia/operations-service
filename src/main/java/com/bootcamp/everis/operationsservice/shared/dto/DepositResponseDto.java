package com.bootcamp.everis.operationsservice.shared.dto;

import com.bootcamp.everis.operationsservice.query.projections.models.Deposit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DepositResponseDto extends OperationResponseDto{
    private String toAccount;
    private String toAccountType;

    static public DepositResponseDto entityToResponse(Deposit deposit) {
        DepositResponseDto depositResponseDto = new DepositResponseDto();
        depositResponseDto.setId(deposit.getId());
        depositResponseDto.setAmount(deposit.getAmount());
        depositResponseDto.setDate(deposit.getDate());
        depositResponseDto.setToAccount(deposit.getToAccount());
        depositResponseDto.setToAccountType(deposit.getToAccountType());
        return depositResponseDto;
    }
}
