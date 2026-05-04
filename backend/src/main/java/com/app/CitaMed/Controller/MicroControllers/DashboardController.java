package com.app.CitaMed.Controller.MicroControllers;
import com.app.CitaMed.DTO.*;
import com.app.CitaMed.Service.MicroServicios.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor

public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping
    public DashboardDTO stats() {
        return dashboardService.obtenerStats();
    }

    @GetMapping("/ultimas-citas")
    public List<UltimaCitaDTO> ultimas() {
        return dashboardService.ultimasCitas();
    }

    @GetMapping("/especialidades")
    public List<EspecialidadDTO> especialidades() {
        return dashboardService.especialidades();
    }

    @GetMapping("/agenda")
    public ResponseEntity<List<AgendaHoyDTO>> agendaHoy() {
        return ResponseEntity.ok(dashboardService.agendaHoy());
    }

    @GetMapping("/medicos")
    public List<MedicoActivoDTO> medicos() {
        return dashboardService.medicos();
    }

    @GetMapping("/pagos")
    public List<UltimoPagoDTO> pagos() {
        return dashboardService.pagos();
    }
}
