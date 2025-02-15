package com.banco.prestamo.mapper;

import com.banco.prestamo.domain.HistorialPrestamo;
import com.banco.prestamo.domain.dto.HistorialPrestamoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HistorialPrestamoMapper {
    HistorialPrestamoMapper INSTANCE = Mappers.getMapper(HistorialPrestamoMapper.class);

    HistorialPrestamoDTO historialPrestamoToHistorialPrestamoDTO(HistorialPrestamo historialPrestamo);
    HistorialPrestamo historialPrestamoDTOToHistorialPrestamo(HistorialPrestamoDTO historialPrestamoDTO);
}