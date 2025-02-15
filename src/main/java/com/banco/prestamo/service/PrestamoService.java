package com.banco.prestamo.service;

import com.banco.prestamo.domain.HistorialPrestamo;
import com.banco.prestamo.domain.Prestamo;
import com.banco.prestamo.domain.SimulacionCuota;
import com.banco.prestamo.domain.dto.*;
import com.banco.prestamo.exception.ResourceNotFoundException;
import com.banco.prestamo.mapper.HistorialPrestamoMapper;
import com.banco.prestamo.mapper.PrestamoMapper;
import com.banco.prestamo.repository.ClienteRepository;
import com.banco.prestamo.repository.HistorialPrestamoRepository;
import com.banco.prestamo.repository.PrestamoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrestamoService {
    private final PrestamoRepository prestamoRepository;
    private final HistorialPrestamoRepository historialRepository;
    private final ClienteRepository clienteRepository;
    private final PrestamoMapper prestamoMapper = PrestamoMapper.INSTANCE;
    private final HistorialPrestamoMapper historialPrestamoMapper = HistorialPrestamoMapper.INSTANCE;

    @Transactional
    public PrestamoDTO solicitarPrestamo(PrestamoDTO prestamoDTO) {
            if (!clienteRepository.existsById(prestamoDTO.getClienteId())) {
            throw new ResourceNotFoundException("Cliente no encontrado");
        }

        Prestamo prestamo = prestamoMapper.prestamoDTOToPrestamo(prestamoDTO);
        prestamo.setClienteId(prestamoDTO.getClienteId());
        prestamo.setMonto(prestamoDTO.getMonto());
        prestamo.setInteres(prestamoDTO.getInteres());
        prestamo.setDuracionMeses(prestamoDTO.getDuracionMeses());
        prestamo.setEstado("Pendiente");
        prestamo = prestamoRepository.save(prestamo);

        HistorialPrestamo historial = new HistorialPrestamo();
        historial.setPrestamoId(prestamo.getId());
        historial.setMontoSolicitado(prestamo.getMonto());
        historial.setEstado("Pendiente");
        historial.setFechaCreacion(LocalDateTime.now());
        historial.setFechaActualizacion(LocalDateTime.now());
        historialRepository.save(historial);

        return prestamoMapper.prestamoToPrestamoDTO(prestamo);
    }

    @Transactional
    public PrestamoDTO aprobarPrestamo(Long prestamoId, String decision) {
        Prestamo prestamo = prestamoRepository.findById(prestamoId)
                .orElseThrow(() -> new ResourceNotFoundException("Préstamo no encontrado"));

        if (!prestamo.getEstado().equals("Pendiente")) {
            throw new IllegalStateException("El préstamo ya no está pendiente");
        }

        prestamo.setEstado(decision.equals("APROBAR") ? "Aprobado" : "Rechazado");
        prestamo = prestamoRepository.save(prestamo);

        HistorialPrestamo historial = new HistorialPrestamo();
        historial.setPrestamoId(prestamo.getId());
        historial.setMontoSolicitado(prestamo.getMonto());
        historial.setEstado(prestamo.getEstado());
        //historial.setFechaCreacion(LocalDateTime.now());
        historial.setFechaActualizacion(LocalDateTime.now());
        historialRepository.save(historial);

        return mapToDTO(prestamo);
    }

    public PrestamoDTO consultarPrestamo(Long prestamoId) {
        Prestamo prestamo = prestamoRepository.findById(prestamoId)
                .orElseThrow(() -> new ResourceNotFoundException("Préstamo no encontrado"));
        return mapToDTO(prestamo);
    }

    public List<PrestamoDTO> consultarUltimosPrestamos(String clienteId) {
        return prestamoRepository.findTop3ByClienteIdOrderByIdDesc(clienteId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<PrestamoDTO> consultarHistorialCliente(String clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            throw new ResourceNotFoundException("Cliente no encontrado");
        }
        return prestamoRepository.findByClienteId(clienteId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public SimulacionCuota simularCuota(BigDecimal monto, BigDecimal interes, Integer duracionMeses) {
        if (duracionMeses == null || duracionMeses == 0) {
            throw new IllegalArgumentException("La duración en meses no puede ser cero o nula");
        }

        // Cálculo con interés simple
        BigDecimal interesTotal = monto.multiply(interes.divide(BigDecimal.valueOf(100)))
                .multiply(BigDecimal.valueOf(duracionMeses))
                .divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP);
        BigDecimal totalPagar = monto.add(interesTotal);
        BigDecimal cuotaMensual = totalPagar.divide(BigDecimal.valueOf(duracionMeses), 2, RoundingMode.HALF_UP);

        SimulacionCuota simulacion = new SimulacionCuota();
        simulacion.setMonto(monto);
        simulacion.setInteres(interes);
        simulacion.setDuracionMeses(duracionMeses);
        simulacion.setCuotaMensual(cuotaMensual);
        simulacion.setTotalPagar(totalPagar);
        return simulacion;
    }

    private PrestamoDTO mapToDTO(Prestamo prestamo) {
        PrestamoDTO dto = new PrestamoDTO();
        dto.setId(prestamo.getId());
        dto.setClienteId(prestamo.getClienteId());
        dto.setMonto(prestamo.getMonto());
        dto.setInteres(prestamo.getInteres());
        dto.setDuracionMeses(prestamo.getDuracionMeses());
        dto.setEstado(prestamo.getEstado());

        // Calcular cuota mensual
        SimulacionCuota simulacion = simularCuota(
                prestamo.getMonto(),
                prestamo.getInteres(),
                prestamo.getDuracionMeses()
        );
        dto.setCuotaMensual(simulacion.getCuotaMensual());

        return dto;
    }
}