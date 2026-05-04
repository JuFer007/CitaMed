package com.app.CitaMed.Service.MicroServicios;
import com.app.CitaMed.DTO.*;
import com.app.CitaMed.Enums.EstadoCita;
import com.app.CitaMed.Repository.Administrativo.PagoRepository;
import com.app.CitaMed.Repository.Agenda.CitaRepository;
import com.app.CitaMed.Repository.Medico.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Component

public class DashboardService {
    private final CitaRepository citaRepository;
    private final PagoRepository pagoRepository;
    private final MedicoRepository medicoRepository;

    public List<AgendaHoyDTO> agendaHoy() {

        LocalDate hoy = LocalDate.now();

        List<Object[]> data = citaRepository.agendaHoyNative(
                hoy.getYear(),
                hoy.getMonthValue(),
                hoy.getDayOfMonth()
        );

        return data.stream().map(obj ->
                new AgendaHoyDTO(
                        obj[0].toString(),
                        obj[1].toString(),
                        obj[2].toString()
                )
        ).toList();
    }

    public List<MedicoActivoDTO> medicos(){
        return medicoRepository.medicosActivos();
    }

    public List<UltimoPagoDTO> pagos(){
        return pagoRepository.ultimosPagos(PageRequest.of(0,4));
    }

    public List<UltimaCitaDTO> ultimasCitas() {
        return citaRepository.ultimasCitas(PageRequest.of(0,6));
    }

    public List<EspecialidadDTO> especialidades() {
        return citaRepository.citasPorEspecialidad();
    }

    public DashboardDTO obtenerStats() {
        LocalDate hoy = LocalDate.now();
        LocalDateTime inicioHoy = hoy.atStartOfDay();
        LocalDateTime finHoy = hoy.plusDays(1).atStartOfDay();
        LocalDateTime inicioAyer = hoy.minusDays(1).atStartOfDay();
        LocalDateTime finAyer = hoy.atStartOfDay();
        LocalDate primerDiaMes = hoy.withDayOfMonth(1);
        LocalDateTime inicioMes = primerDiaMes.atStartOfDay();
        LocalDateTime finMes = hoy.plusDays(1).atStartOfDay();
        LocalDate primerMesAnterior = primerDiaMes.minusMonths(1);
        LocalDate ultimoMesAnterior = primerDiaMes.minusDays(1);
        LocalDateTime inicioMesAnt = primerMesAnterior.atStartOfDay();
        LocalDateTime finMesAnt = ultimoMesAnterior.plusDays(1).atStartOfDay();
        LocalDate lunes = hoy.minusDays(hoy.getDayOfWeek().getValue() - 1);
        LocalDateTime inicioSemana = lunes.atStartOfDay();
        LocalDateTime finSemana = hoy.plusDays(1).atStartOfDay();
        LocalDateTime inicioSemanaAnt = lunes.minusWeeks(1).atStartOfDay();
        LocalDateTime finSemanaAnt = lunes.atStartOfDay();

        return new DashboardDTO(
                citaRepository.countByFechaHoraBetween(inicioHoy, finHoy),
                citaRepository.countByFechaHoraBetween(inicioAyer, finAyer),
                citaRepository.pacientesActivos(inicioMes, finMes),
                citaRepository.pacientesActivos(inicioMesAnt, finMesAnt),
                pagoRepository.ingresos(inicioMes, finMes),
                pagoRepository.ingresos(inicioMesAnt, finMesAnt),
                citaRepository.countByFechaHoraBetweenAndEstado(inicioSemana, finSemana, EstadoCita.CANCELADA),
                citaRepository.countByFechaHoraBetweenAndEstado(inicioSemanaAnt, finSemanaAnt, EstadoCita.CANCELADA)
        );
    }
}
