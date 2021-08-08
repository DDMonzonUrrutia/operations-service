package com.bootcamp.everis.operationsservice.shared.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OperationResponseDto {
    private String id;
    private Double amount;
    private LocalDateTime date;
}
