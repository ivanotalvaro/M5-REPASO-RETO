package com.banco.prestamo.controller;
import com.banco.prestamo.domain.dto.*;
import com.banco.prestamo.service.PrestamoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
@RequiredArgsConstructor
public class PrestamoController {
    private final PrestamoService prestamoService;

    @PostMapping
    public ResponseEntity<PrestamoDTO> solicitarPrestamo(@RequestBody PrestamoDTO prestamoDTO) {
        return ResponseEntity.ok(prestamoService.solicitarPrestamo(prestamoDTO));
    }

    @PutMapping("/{id}/aprobar")
    public ResponseEntity<PrestamoDTO> aprobarPrestamo(
            @PathVariable Long id,
            @RequestParam String decision) {
        return ResponseEntity.ok(prestamoService.aprobarPrestamo(id, decision));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestamoDTO> consultarPrestamo(@PathVariable Long id) {
        return ResponseEntity.ok(prestamoService.consultarPrestamo(id));
    }

    @GetMapping("/cliente/{clienteId}/ultimos")
    public ResponseEntity<List<PrestamoDTO>> consultarUltimosPrestamos(
            @PathVariable String clienteId) {
        return ResponseEntity.ok(prestamoService.consultarUltimosPrestamos(clienteId));
    }

    @GetMapping("/cliente/{clienteId}/historial")
    public ResponseEntity<List<PrestamoDTO>> consultarHistorialCliente(
            @PathVariable String clienteId) {
        return ResponseEntity.ok(prestamoService.consultarHistorialCliente(clienteId));
    }

    @GetMapping("/simular")
    public ResponseEntity<SimulacionCuotaDTO> simularCuota(
            @RequestParam BigDecimal monto,
            @RequestParam BigDecimal interes,
            @RequestParam Integer duracionMeses) {
        return ResponseEntity.ok(prestamoService.simularCuota(monto, interes, duracionMeses));
    }
}