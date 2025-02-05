package com.banco.prestamo.repository;

import com.banco.prestamo.domain.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    List<Prestamo> findTop3ByClienteIdOrderByIdDesc(String clienteId);
    List<Prestamo> findByClienteId(String clienteId);
}