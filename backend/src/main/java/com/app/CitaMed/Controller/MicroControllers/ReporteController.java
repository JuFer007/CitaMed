package com.app.CitaMed.Controller.MicroControllers;
import com.app.CitaMed.DTO.*;
import com.app.CitaMed.Service.MicroServicios.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor

public class ReporteController {
    
    private final ReporteService reporteService;

    @GetMapping("/citas-por-mes")
    public List<ReportesCitadMesDTO> citasPorMes(
            @RequestParam(required = false) Integer anio,
            @RequestParam(required = false) Integer mes,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        return reporteService.citasPorMes(anio, mes, fechaInicio, fechaFin);
    }

    @GetMapping("/ingresos-por-mes")
    public List<ReporteIngresoMesDTO> ingresosPorMes(
            @RequestParam(required = false) Integer anio,
            @RequestParam(required = false) Integer mes,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        return reporteService.ingresosPorMes(anio, mes, fechaInicio, fechaFin);
    }

    @GetMapping("/citas-por-estado")
    public List<ReporteEstadoDTO> citasPorEstado(
            @RequestParam(required = false) Integer anio,
            @RequestParam(required = false) Integer mes,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        return reporteService.citasPorEstado(anio, mes, fechaInicio, fechaFin);
    }

    @GetMapping("/citas-por-especialidad")
    public List<EspecialidadDTO> citasPorEspecialidad(
            @RequestParam(required = false) Integer anio,
            @RequestParam(required = false) Integer mes,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        return reporteService.citasPorEspecialidad(anio, mes, fechaInicio, fechaFin);
    }

    @GetMapping("/top-medicos")
    public List<MedicoActivoDTO> topMedicos(
            @RequestParam(required = false) Integer anio,
            @RequestParam(required = false) Integer mes,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        return reporteService.topMedicos(anio, mes, fechaInicio, fechaFin);
    }
}