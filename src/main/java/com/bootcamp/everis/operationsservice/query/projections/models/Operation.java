package com.bootcamp.everis.operationsservice.query.projections.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class Operation {
    @Id
    private String id;
    private Double amount;
    private LocalDateTime date;
}
