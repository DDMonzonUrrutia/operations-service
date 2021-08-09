package com.bootcamp.everis.operationsservice.shared.contracts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreditProductTypeDto {
    private String id;
    private String description;
    private int valtip;
}
