package com.banco.prestamo.mapper;


import com.banco.prestamo.domain.Cliente;
import com.banco.prestamo.domain.dto.ClienteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClienteMapper {
    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    ClienteDTO clienteToClienteDTO(Cliente cliente);
    Cliente clienteDTOToCliente(ClienteDTO clienteDTO);
}