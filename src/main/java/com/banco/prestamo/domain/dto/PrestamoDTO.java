package com.banco.prestamo.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PrestamoDTO {
    private Long id;
    private String clienteId;
    private BigDecimal monto;
    private BigDecimal interes;
    private Integer duracionMeses;
    private String estado;
    private BigDecimal cuotaMensual;
}