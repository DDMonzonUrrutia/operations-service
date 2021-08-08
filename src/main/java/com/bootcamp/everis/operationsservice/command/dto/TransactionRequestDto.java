package com.bootcamp.everis.operationsservice.command.dto;

import com.bootcamp.everis.operationsservice.query.projections.models.Transaction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransactionRequestDto extends OperationRequestDto{
    private String toAccount;
    private String fromAccount;
    private String fromAccountType;
    private String toAccountType;

    static public Transaction requestToEntity(TransactionRequestDto requestDto) {
        Transaction transaction = new Transaction();
        transaction.setAmount(requestDto.getAmount());
        transaction.setFromAccount(requestDto.getFromAccount());
        transaction.setToAccount(requestDto.getToAccount());
        transaction.setFromAccountType(requestDto.getFromAccountType());
        transaction.setToAccountType(requestDto.getToAccountType());
        return transaction;
    }
}
