package com.example.backend.models.request;

import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BeneficioRequest {
    private String nome;
    private String descricao;
    private BigDecimal valor;
}
