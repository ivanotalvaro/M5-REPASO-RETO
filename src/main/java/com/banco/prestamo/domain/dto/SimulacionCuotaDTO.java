package com.banco.prestamo.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SimulacionCuotaDTO {
    private BigDecimal monto;
    private BigDecimal interes;
    private Integer duracionMeses;
    private BigDecimal cuotaMensual;
    private BigDecimal totalPagar;
}