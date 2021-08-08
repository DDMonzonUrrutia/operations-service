package com.bootcamp.everis.operationsservice.query.projections.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document
public class Purchase extends Operation{
    private String creditCard;
    private String business;
}
