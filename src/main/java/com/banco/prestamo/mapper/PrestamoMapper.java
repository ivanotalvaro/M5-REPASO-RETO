package com.banco.prestamo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.banco.prestamo.domain.Prestamo;
import com.banco.prestamo.domain.dto.PrestamoDTO;

@Mapper
public interface PrestamoMapper {
    PrestamoMapper INSTANCE = Mappers.getMapper(PrestamoMapper.class);

    PrestamoDTO prestamoToPrestamoDTO(Prestamo prestamo);
    Prestamo prestamoDTOToPrestamo(PrestamoDTO prestamoDTO);
}