package com.bootcamp.everis.operationsservice.command.dto;

import com.bootcamp.everis.operationsservice.query.projections.models.Retire;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RetireRequestDto extends OperationRequestDto{
    private String fromAccount;
    private String fromAccountType;

    static public Retire requestToEntity(RetireRequestDto requestDto) {
        Retire retire = new Retire();
        retire.setAmount(requestDto.getAmount());
        retire.setFromAccount(requestDto.getFromAccount());
        retire.setFromAccountType(requestDto.getFromAccountType());
        return retire;
    }
}
