package com.app.CitaMed.Controller.MicroControllers;

import com.app.CitaMed.DTO.*;
import com.app.CitaMed.Service.MicroServicios.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteController {
    
    private final ReporteService reporteService;

    @GetMapping("/citas-por-mes")
    public List<ReportesCitadMesDTO> citasPorMes(@RequestParam(required = false) Integer anio) {
        int anioConsulta = (anio != null) ? anio : LocalDate.now().getYear();
        return reporteService.citasPorMes(anioConsulta);
    }

    @GetMapping("/ingresos-por-mes")
    public List<ReporteIngresoMesDTO> ingresosPorMes(@RequestParam(required = false) Integer anio) {
        int anioConsulta = (anio != null) ? anio : LocalDate.now().getYear();
        return reporteService.ingresosPorMes(anioConsulta);
    }

    @GetMapping("/citas-por-estado")
    public List<ReporteEstadoDTO> citasPorEstado(@RequestParam(required = false) Integer anio) {
        int anioConsulta = (anio != null) ? anio : LocalDate.now().getYear();
        return reporteService.citasPorEstado(anioConsulta);
    }

    @GetMapping("/citas-por-especialidad")
    public List<EspecialidadDTO> citasPorEspecialidad(@RequestParam(required = false) Integer anio) {
        int anioConsulta = (anio != null) ? anio : LocalDate.now().getYear();
        return reporteService.citasPorEspecialidad(anioConsulta);
    }

    @GetMapping("/top-medicos")
    public List<MedicoActivoDTO> topMedicos(@RequestParam(required = false) Integer anio) {
        int anioConsulta = (anio != null) ? anio : LocalDate.now().getYear();
        return reporteService.topMedicos(anioConsulta);
    }
}
