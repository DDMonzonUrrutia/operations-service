package com.bootcamp.everis.operationsservice.shared.contracts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientTypeDto {
    private String id;
    private String name;
    private Integer valtip;
}
