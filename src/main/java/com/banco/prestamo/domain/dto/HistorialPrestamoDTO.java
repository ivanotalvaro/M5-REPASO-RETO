package com.banco.prestamo.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class HistorialPrestamoDTO {
    @NotNull(message = "El ID no puede estar vacío")
    private Long id;

    @NotNull(message = "El ID del préstamo no puede estar vacío")
    private Long prestamoId;

    @NotNull(message = "El monto solicitado no puede estar vacío")
    private BigDecimal montoSolicitado;

    @NotNull(message = "El estado no puede estar vacío")
    @Size(max = 10, message = "El estado no puede tener más de 10 caracteres")
    private String estado;

    @NotNull(message = "La fecha de creación no puede estar vacía")
    private String fechaCreacion;

    @NotNull(message = "La fecha de actualización no puede estar vacía")
    private String fechaActualizacion;
}