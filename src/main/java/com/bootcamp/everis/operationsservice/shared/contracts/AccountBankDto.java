package com.bootcamp.everis.operationsservice.shared.contracts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountBankDto {
    private String id;
    private String numCount;
    private ClientDto client;
    private ProductBankDto product ;
    private double balance;
    private boolean state;
}
