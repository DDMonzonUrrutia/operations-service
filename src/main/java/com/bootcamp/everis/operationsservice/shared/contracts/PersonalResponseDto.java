package com.bootcamp.everis.operationsservice.shared.contracts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PersonalResponseDto {
    private String id;
    private String phone;
    private String firstname;
    private String lastname;
    private String doi;
}
