package com.app.CitaMed.Service.MicroServicios;
import com.app.CitaMed.DTO.*;
import com.app.CitaMed.Repository.Administrativo.PagoRepository;
import com.app.CitaMed.Repository.Agenda.CitaRepository;
import com.app.CitaMed.Repository.Medico.MedicoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
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

    private LocalDateTime[] calcularRango(Integer anio, Integer mes, LocalDate fechaInicio, LocalDate fechaFin) {
        LocalDateTime inicio;
        LocalDateTime fin;

        if (fechaInicio != null && fechaFin != null) {
            inicio = fechaInicio.atStartOfDay();
            fin = fechaFin.atTime(LocalTime.MAX);
        } else if (mes != null) {
            int year = (anio != null) ? anio : LocalDate.now().getYear();
            YearMonth yearMonth = YearMonth.of(year, mes);
            inicio = yearMonth.atDay(1).atStartOfDay();
            fin = yearMonth.atEndOfMonth().atTime(LocalTime.MAX);
        } else {
            int year = (anio != null) ? anio : LocalDate.now().getYear();
            inicio = LocalDate.of(year, 1, 1).atStartOfDay();
            fin = LocalDate.of(year, 12, 31).atTime(LocalTime.MAX);
        }

        return new LocalDateTime[]{inicio, fin};
    }

    public List<ReportesCitadMesDTO> citasPorMes(Integer anio, Integer mes, LocalDate fechaInicio, LocalDate fechaFin) {
        LocalDateTime[] rango = calcularRango(anio, mes, fechaInicio, fechaFin);
        log.info("Consultando citas por mes entre {} y {}.", rango[0], rango[1]);
        Map<Integer, Long> datos = new HashMap<>();
        for (Object[] fila : citaRepository.citasPorMesBetween(rango[0], rango[1])) {
            datos.put(((Number) fila[0]).intValue(), ((Number) fila[1]).longValue());
        }
        List<ReportesCitadMesDTO> resultado = new ArrayList<>();
        int mesInicio = rango[0].getMonthValue();
        int mesFin = rango[1].getMonthValue();
        for (int m = mesInicio; m <= mesFin; m++) {
            resultado.add(new ReportesCitadMesDTO(NOMBRES_MES[m - 1], datos.getOrDefault(m, 0L)));
        }
        return resultado;
    }

    public List<ReporteIngresoMesDTO> ingresosPorMes(Integer anio, Integer mes, LocalDate fechaInicio, LocalDate fechaFin) {
        LocalDateTime[] rango = calcularRango(anio, mes, fechaInicio, fechaFin);
        log.info("Consultando ingresos por mes entre {} y {}.", rango[0], rango[1]);
        Map<Integer, Double> datos = new HashMap<>();
        for (Object[] fila : pagoRepository.ingresosPorMesBetween(rango[0], rango[1])) {
            datos.put(((Number) fila[0]).intValue(), ((Number) fila[1]).doubleValue());
        }
        List<ReporteIngresoMesDTO> resultado = new ArrayList<>();
        int mesInicio = rango[0].getMonthValue();
        int mesFin = rango[1].getMonthValue();
        for (int m = mesInicio; m <= mesFin; m++) {
            resultado.add(new ReporteIngresoMesDTO(NOMBRES_MES[m - 1], datos.getOrDefault(m, 0.0)));
        }
        return resultado;
    }

    public List<ReporteEstadoDTO> citasPorEstado(Integer anio, Integer mes, LocalDate fechaInicio, LocalDate fechaFin) {
        LocalDateTime[] rango = calcularRango(anio, mes, fechaInicio, fechaFin);
        log.info("Consultando citas por estado entre {} y {}.", rango[0], rango[1]);
        return citaRepository.citasPorEstadoBetween(rango[0], rango[1]);
    }

    public List<EspecialidadDTO> citasPorEspecialidad(Integer anio, Integer mes, LocalDate fechaInicio, LocalDate fechaFin) {
        LocalDateTime[] rango = calcularRango(anio, mes, fechaInicio, fechaFin);
        log.info("Consultando citas por especialidad entre {} y {}.", rango[0], rango[1]);
        return citaRepository.citasPorEspecialidadBetween(rango[0], rango[1]);
    }

    public List<MedicoActivoDTO> topMedicos(Integer anio, Integer mes, LocalDate fechaInicio, LocalDate fechaFin) {
        LocalDateTime[] rango = calcularRango(anio, mes, fechaInicio, fechaFin);
        log.info("Consultando top médicos entre {} y {}.", rango[0], rango[1]);
        return medicoRepository.medicosMasActivosBetween(rango[0], rango[1], PageRequest.of(0, 5));
    }
    
}