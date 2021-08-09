package com.bootcamp.everis.operationsservice.command.dto;

import com.bootcamp.everis.operationsservice.query.projections.models.Deposit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DepositRequestDto extends OperationRequestDto{
    private String toAccount;
    private String toAccountType;

    static public Deposit requestToEntity(DepositRequestDto requestDto) {
        Deposit deposit = new Deposit();
        deposit.setAmount(requestDto.getAmount());
        deposit.setToAccount(requestDto.getToAccount());
        deposit.setToAccountType(requestDto.getToAccountType());
        return deposit;
    }
}
