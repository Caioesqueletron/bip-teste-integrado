package com.example.backend.models.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferenciaRequest {
    private Long fromId;
    private Long toId;
    private BigDecimal amount;
}
