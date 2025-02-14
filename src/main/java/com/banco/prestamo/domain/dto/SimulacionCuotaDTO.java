package com.banco.prestamo.domain.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
public class SimulacionCuotaDTO {
    @NotNull(message = "El monto no puede estar vacío")
    @DecimalMin(value = "0.0", inclusive = false, message = "El monto debe ser mayor que cero")
    private BigDecimal monto;

    @NotNull(message = "El interés no puede estar vacío")
    @DecimalMin(value = "0.0", inclusive = false, message = "El interés debe ser mayor que cero")
    private BigDecimal interes;

    @NotNull(message = "La duración en meses no puede estar vacía")
    @Min(value = 1, message = "La duración debe ser al menos de 1 mes")
    private Integer duracionMeses;

    @NotNull(message = "La cuota mensual no puede estar vacía")
    @DecimalMin(value = "0.0", inclusive = false, message = "La cuota mensual debe ser mayor que cero")
    private BigDecimal cuotaMensual;

    @NotNull(message = "El total a pagar no puede estar vacío")
    @DecimalMin(value = "0.0", inclusive = false, message = "El total a pagar debe ser mayor que cero")
    private BigDecimal totalPagar;
}