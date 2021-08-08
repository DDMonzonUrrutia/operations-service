package com.bootcamp.everis.operationsservice.shared.dto;

import com.bootcamp.everis.operationsservice.query.projections.models.Transaction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransactionResponseDto extends OperationResponseDto{
    private String toAccount;
    private String fromAccount;

    static public TransactionResponseDto entityToResponse(Transaction transaction) {
        TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
        transactionResponseDto.setId(transaction.getId());
        transactionResponseDto.setAmount(transaction.getAmount());
        transactionResponseDto.setDate(transaction.getDate());
        transactionResponseDto.setFromAccount(transaction.getFromAccount());
        transactionResponseDto.setToAccount(transaction.getToAccount());
        return transactionResponseDto;
    }
}
