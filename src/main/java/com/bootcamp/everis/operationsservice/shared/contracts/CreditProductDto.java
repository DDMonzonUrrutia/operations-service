package com.bootcamp.everis.operationsservice.shared.contracts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreditProductDto {
    private String id;
    private String description;
    private double interest;
    private CreditProductTypeDto typeProductCredit;
    private Integer code;
}
