package com.app.CitaMed.Service.MicroServicios;
import com.app.CitaMed.DTO.*;
import com.app.CitaMed.Repository.Administrativo.PagoRepository;
import com.app.CitaMed.Repository.Agenda.CitaRepository;
import com.app.CitaMed.Repository.Medico.MedicoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j

public class ReporteService {
    private final CitaRepository citaRepository;
    private final PagoRepository pagoRepository;
    private final MedicoRepository medicoRepository;

    private static final String[] NOMBRES_MES = {
        "Ene", "Feb", "Mar", "Abr", "May", "Jun",
        "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"
    };

    public List<ReportesCitadMesDTO> citasPorMes(int año) {
        log.info("Consultando citas por mes para el año {}.", año);
        Map<Integer, Long> datos = new HashMap<>();
        for (Object[] fila : citaRepository.citasPorMesNative(año)) {
            datos.put(((Number) fila[0]).intValue(), ((Number) fila[1]).longValue());
        }
        List<ReportesCitadMesDTO> resultado = new ArrayList<>();
        for (int mes = 1; mes <= 12; mes++) {
            resultado.add(new ReportesCitadMesDTO(NOMBRES_MES[mes - 1], datos.getOrDefault(mes, 0L)));
        }
        return resultado;
    }

    public List<ReporteIngresoMesDTO> ingresosPorMes(int año) {
        log.info("Consultando ingresos por mes para el año {}.", año);
        Map<Integer, Double> datos = new HashMap<>();
        for (Object[] fila : pagoRepository.ingresosPorMesNative(año)) {
            datos.put(((Number) fila[0]).intValue(), ((Number) fila[1]).doubleValue());
        }
        List<ReporteIngresoMesDTO> resultado = new ArrayList<>();
        for (int mes = 1; mes <= 12; mes++) {
            resultado.add(new ReporteIngresoMesDTO(NOMBRES_MES[mes - 1], datos.getOrDefault(mes, 0.0)));
        }
        return resultado;
    }

    public List<ReporteEstadoDTO> citasPorEstado(int anio) {
        log.info("Consultando citas agrupadas por estado para el año {}.", anio);
        return citaRepository.citasPorEstadoPorAnio(anio);
    }

    public List<EspecialidadDTO> citasPorEspecialidad(int anio) {
        log.info("Consultando citas agrupadas por especialidad para el año {}.", anio);
        return citaRepository.citasPorEspecialidadPorAnio(anio);
    }

    public List<MedicoActivoDTO> topMedicos(int anio) {
        log.info("Consultando el top 5 de médicos con más citas para el año {}.", anio);
        return medicoRepository.medicosMasActivosPorAnio(anio, PageRequest.of(0, 5));
    }
    
}