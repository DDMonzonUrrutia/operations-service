package com.bootcamp.everis.operationsservice.shared.dto;

import com.bootcamp.everis.operationsservice.query.projections.models.Deposit;
import com.bootcamp.everis.operationsservice.query.projections.models.Retire;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RetireResponseDto extends OperationResponseDto{
    private String fromAccount;
    private String fromAccountType;

    static public RetireResponseDto entityToResponse(Retire deposit) {
        RetireResponseDto retireResponseDto = new RetireResponseDto();
        retireResponseDto.setId(deposit.getId());
        retireResponseDto.setAmount(deposit.getAmount());
        retireResponseDto.setDate(deposit.getDate());
        retireResponseDto.setFromAccount(deposit.getFromAccount());
        retireResponseDto.setFromAccountType(deposit.getFromAccountType());
        return retireResponseDto;
    }
}
