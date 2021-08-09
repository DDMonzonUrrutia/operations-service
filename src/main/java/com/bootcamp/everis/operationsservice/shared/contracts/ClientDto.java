package com.bootcamp.everis.operationsservice.shared.contracts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientDto {
    private String id;
    private String dni;
    private String firstName ;
    private String lastName;
    private boolean state ;
    private ClientTypeDto type;
}
