package com.app.CitaMed.Service.Portal;

import com.app.CitaMed.DTO.HistorialMedicoDetalleDTO;
import com.app.CitaMed.Service.Paciente.HistorialMedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PortalHistorialService {

    private final HistorialMedicoService historialMedicoService;

    public HistorialMedicoDetalleDTO obtenerHistorialCompleto(Long pacienteId) {
        HistorialMedicoDetalleDTO historial = historialMedicoService.obtenerHistorialCompleto(pacienteId);
        if (historial == null) {
            throw new RuntimeException("Paciente no encontrado");
        }
        return historial;
    }
}
