package com.banco.prestamo.domain.dto;

import lombok.Data;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Data
public class PrestamoDTO {
    private Long id;

    @NotBlank(message = "El ID del cliente no puede estar vacío")
    private String clienteId;

    @NotNull(message = "El monto no puede estar vacío")
    @DecimalMin(value = "0.0", inclusive = false, message = "El monto debe ser mayor que cero")
    private BigDecimal monto;

    @NotNull(message = "El interés no puede estar vacío")
    @DecimalMin(value = "0.0", inclusive = false, message = "El interés debe ser mayor que cero")
    private BigDecimal interes;

    @NotNull(message = "La duración en meses no puede estar vacía")
    @Min(value = 1, message = "La duración debe ser al menos de 1 mes")
    private Integer duracionMeses;

    private String estado;
    private BigDecimal cuotaMensual;
}