package com.banco.prestamo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "prestamos")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cliente_id")
    private String clienteId;

    @DecimalMin(value = "0.0", message = "El monto debe ser mayor a 0")
    private BigDecimal monto;

    @DecimalMin(value = "0.0", message = "El interés debe ser mayor a 0")
    @DecimalMax(value = "100.0", message = "El interés no puede ser mayor a 100")
    private BigDecimal interes;

    @Min(value = 1, message = "La duración debe ser de al menos 1 mes")
    @Column(name = "duracion_meses")
    private Integer duracionMeses;

    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", insertable = false, updatable = false)
    private Cliente cliente;
}