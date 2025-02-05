package com.banco.prestamo.repository;


import com.banco.prestamo.domain.HistorialPrestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HistorialPrestamoRepository extends JpaRepository<HistorialPrestamo, Long> {
    List<HistorialPrestamo> findByPrestamoIdOrderByFechaCreacionDesc(Long prestamoId);
}