package com.banco.prestamo.domain.dto;

import lombok.Data;

@Data
public class ClienteDTO {
    private String id;
    private String nombre;
    private String correo;
    private String telefono;
    private String direccion;
}