package com.banco.prestamo.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HistorialPrestamoDTO {
    private Long id;
    private Long prestamoId;
    private BigDecimal montoSolicitado;
    private String estado;
    private String fechaCreacion;
    private String fechaActualizacion;
}