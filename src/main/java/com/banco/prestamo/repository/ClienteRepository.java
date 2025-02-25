package com.banco.prestamo.repository;

import com.banco.prestamo.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
    boolean existsByCorreo(String correo);
}