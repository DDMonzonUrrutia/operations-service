package com.bootcamp.everis.operationsservice.query.projections.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document
public class Deposit extends Operation{
    private String toAccount;
    private String toAccountType;
}
