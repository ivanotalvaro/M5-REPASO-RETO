package com.banco.prestamo.domain.dto;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class HistorialPrestamoDTO {
    @NotNull(message = "El ID no puede estar vacío")
    private Long id;

    @NotNull(message = "El ID del préstamo no puede estar vacío")
    private Long prestamoId;

    private BigDecimal montoSolicitado;
    private String estado;
    private String fechaCreacion;
    private String fechaActualizacion;
}