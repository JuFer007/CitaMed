package com.app.CitaMed.Service.Agenda;

import com.app.CitaMed.DTO.CitaDTO;
import com.app.CitaMed.DTO.CitaDetalleDTO;
import com.app.CitaMed.Enums.*;
import com.app.CitaMed.Model.Administrativo.Consultorio;
import com.app.CitaMed.Model.Administrativo.Pago;
import com.app.CitaMed.Model.Agenda.Cita;
import com.app.CitaMed.Model.Agenda.HorarioMedico;
import com.app.CitaMed.Model.Medico.Especialidad;
import com.app.CitaMed.Model.Medico.Medico;
import com.app.CitaMed.Model.Paciente.Paciente;
import com.app.CitaMed.Repository.Administrativo.PagoRepository;
import com.app.CitaMed.Repository.Agenda.CitaRepository;
import com.app.CitaMed.Repository.Agenda.HorarioMedicoRepository;
import com.app.CitaMed.Repository.Medico.MedicoRepository;
import com.app.CitaMed.Repository.Paciente.PacienteRepository;
import com.app.CitaMed.Service.MicroServicios.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CitaServiceTest {

    @Mock private CitaRepository citaRepository;
    @Mock private PacienteRepository pacienteRepository;
    @Mock private MedicoRepository medicoRepository;
    @Mock private EmailService emailService;
    @Mock private PagoRepository pagoRepository;
    @Mock private HorarioMedicoRepository horarioMedicoRepository;

    @InjectMocks
    private CitaService citaService;

    private Paciente paciente;
    private Medico medico;
    private Consultorio consultorio;
    private Cita citaProgramada;
    private CitaDTO citaDTO;
    private LocalDateTime fechaValida;

    @BeforeEach
    void setUp() {
        Especialidad especialidad = new Especialidad(1L, "Cardiología", "Cardiología general");
        consultorio = new Consultorio(1L, "101", "Consultorio principal", true, especialidad);
        paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNombre("JUAN");
        paciente.setApellidoPaterno("PEREZ");
        paciente.setActivo(true);

        medico = new Medico();
        medico.setId(1L);
        medico.setNombre("Dr. Carlos");
        medico.setApellidoPaterno("Lopez");
        medico.setActivo(true);
        medico.setConsultorio(consultorio);
        medico.setEspecialidad(especialidad);

        fechaValida = LocalDateTime.of(2026, 6, 22, 10, 0);

        citaProgramada = new Cita();
        citaProgramada.setId(1L);
        citaProgramada.setPaciente(paciente);
        citaProgramada.setMedico(medico);
        citaProgramada.setConsultorio(consultorio);
        citaProgramada.setFechaHora(fechaValida);
        citaProgramada.setMotivoConsulta("Control general");
        citaProgramada.setEstado(EstadoCita.PROGRAMADA);

        citaDTO = new CitaDTO();
        citaDTO.setPacienteId(1L);
        citaDTO.setMedicoId(1L);
        citaDTO.setFechaHora(fechaValida);
        citaDTO.setMotivoConsulta("Control general");
    }

    @Test
    void save_deberiaRetornarError_CuandoPacienteNoExiste() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.empty());

        String result = citaService.save(citaDTO);

        assertEquals("Paciente no encontrado", result);
        verify(citaRepository, never()).save(any());
    }

    @Test
    void save_deberiaRetornarError_CuandoMedicoNoExiste() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(medicoRepository.findById(1L)).thenReturn(Optional.empty());

        String result = citaService.save(citaDTO);

        assertEquals("Médico no encontrado", result);
    }

    @Test
    void save_deberiaRetornarError_CuandoMedicoNoEstaActivo() {
        medico.setActivo(false);
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));

        String result = citaService.save(citaDTO);

        assertEquals("El médico no está activo", result);
    }

    @Test
    void save_deberiaRetornarError_CuandoMedicoNoTieneConsultorio() {
        medico.setConsultorio(null);
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));

        String result = citaService.save(citaDTO);

        assertEquals("El médico no tiene un consultorio asignado", result);
    }

    @Test
    void save_deberiaRetornarError_CuandoFechaEsSabado() {
        citaDTO.setFechaHora(LocalDateTime.of(2026, 6, 20, 10, 0));
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));

        String result = citaService.save(citaDTO);

        assertTrue(result.contains("lunes a viernes"));
    }

    @Test
    void save_deberiaRetornarError_CuandoHorarioNoCoincideConMedico() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));
        when(horarioMedicoRepository.findByMedicoIdAndActivoTrue(1L)).thenReturn(List.of());

        String result = citaService.save(citaDTO);

        assertTrue(result.contains("no tiene horarios"));
    }

    @Test
    void save_deberiaCrearCitaYFactura_CuandoTodoEsValido() {
        HorarioMedico horario = new HorarioMedico();
        horario.setDia(DiaSemana.LUNES);
        horario.setHoraInicio(LocalTime.of(8, 0));
        horario.setHoraFin(LocalTime.of(12, 0));

        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));
        when(horarioMedicoRepository.findByMedicoIdAndActivoTrue(1L)).thenReturn(List.of(horario));
        when(citaRepository.countOverlapByMedico(any(), eq(EstadoCita.CANCELADA), any(), any())).thenReturn(0L);
        when(citaRepository.countOverlapByPaciente(any(), eq(EstadoCita.CANCELADA), any(), any())).thenReturn(0L);
        when(citaRepository.save(any(Cita.class))).thenReturn(citaProgramada);

        String result = citaService.save(citaDTO);

        assertEquals("Cita registrada correctamente", result);
        verify(citaRepository, times(1)).save(any(Cita.class));
        verify(pagoRepository, times(1)).save(any(Pago.class));
        verify(emailService, times(1)).enviarConfirmacion(any(Cita.class));
    }

    @Test
    void cancelar_deberiaRetornarError_CuandoCitaNoExiste() {
        when(citaRepository.findById(99L)).thenReturn(Optional.empty());

        String result = citaService.cancelar(99L);

        assertEquals("Cita no encontrada", result);
    }

    @Test
    void cancelar_deberiaRetornarError_CuandoCitaYaFueAtendida() {
        citaProgramada.setEstado(EstadoCita.ATENDIDA);
        when(citaRepository.findById(1L)).thenReturn(Optional.of(citaProgramada));

        String result = citaService.cancelar(1L);

        assertEquals("No se puede cancelar una cita ya atendida", result);
    }

    @Test
    void cancelar_deberiaRetornarError_CuandoCitaYaEstaCancelada() {
        citaProgramada.setEstado(EstadoCita.CANCELADA);
        when(citaRepository.findById(1L)).thenReturn(Optional.of(citaProgramada));

        String result = citaService.cancelar(1L);

        assertEquals("La cita ya está cancelada", result);
    }

    @Test
    void cancelar_deberiaCancelarCitaYAnularPago_CuandoPagoEstaPendiente() {
        Pago pago = new Pago();
        pago.setId(1L);
        pago.setCita(citaProgramada);
        pago.setEstado(EstadoPago.PENDIENTE);
        citaProgramada.setPago(pago);

        when(citaRepository.findById(1L)).thenReturn(Optional.of(citaProgramada));
        when(pagoRepository.findByCitaId(1L)).thenReturn(Optional.of(pago));

        String result = citaService.cancelar(1L);

        assertEquals("Cita cancelada correctamente", result);
        assertEquals(EstadoCita.CANCELADA, citaProgramada.getEstado());
        assertEquals(EstadoPago.ANULADO, pago.getEstado());
        verify(emailService, times(1)).enviarCancelacion(any(Cita.class));
    }

    @Test
    void completar_deberiaCompletarCita() {
        when(citaRepository.findById(1L)).thenReturn(Optional.of(citaProgramada));

        String result = citaService.completar(1L);

        assertEquals("Cita completada correctamente", result);
        assertEquals(EstadoCita.ATENDIDA, citaProgramada.getEstado());
    }

    @Test
    void completar_deberiaRetornarError_CuandoCitaEstaCancelada() {
        citaProgramada.setEstado(EstadoCita.CANCELADA);
        when(citaRepository.findById(1L)).thenReturn(Optional.of(citaProgramada));

        String result = citaService.completar(1L);

        assertEquals("No se puede completar una cita cancelada", result);
    }

    @Test
    void noAsistio_deberiaMarcarCitaComoNoAsistida() {
        when(citaRepository.findById(1L)).thenReturn(Optional.of(citaProgramada));

        String result = citaService.noAsistio(1L);

        assertEquals("Cita marcada como no asistida", result);
        assertEquals(EstadoCita.NO_ASISTIO, citaProgramada.getEstado());
    }

    @Test
    void findById_deberiaRetornarCita_CuandoExiste() {
        when(citaRepository.findById(1L)).thenReturn(Optional.of(citaProgramada));

        Cita result = citaService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void findById_deberiaRetornarNull_CuandoNoExiste() {
        when(citaRepository.findById(99L)).thenReturn(Optional.empty());

        assertNull(citaService.findById(99L));
    }

    @Test
    void findAll_deberiaRetornarListaDeCitas() {
        when(citaRepository.findAll()).thenReturn(List.of(citaProgramada));

        List<Cita> result = citaService.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void findAllDetalle_deberiaRetornarListaDeDetalles() {
        CitaDetalleDTO detalle = new CitaDetalleDTO();
        when(citaRepository.findAllDetalle()).thenReturn(List.of(detalle));

        List<CitaDetalleDTO> result = citaService.findAllDetalle();

        assertFalse(result.isEmpty());
    }

    @Test
    void eliminar_deberiaEliminarCitaYSuPago() {
        Pago pago = new Pago();
        pago.setId(1L);
        citaProgramada.setPago(pago);

        when(citaRepository.findById(1L)).thenReturn(Optional.of(citaProgramada));

        String result = citaService.eliminar(1L);

        assertEquals("Cita eliminada correctamente", result);
        verify(pagoRepository, times(1)).delete(pago);
        verify(citaRepository, times(1)).delete(citaProgramada);
    }

    @Test
    void reprogramar_deberiaReprogramarCita_CuandoTodoEsValido() {
        HorarioMedico horario = new HorarioMedico();
        horario.setDia(DiaSemana.LUNES);
        horario.setHoraInicio(LocalTime.of(8, 0));
        horario.setHoraFin(LocalTime.of(18, 0));
        LocalDateTime nuevaFecha = LocalDateTime.of(2026, 6, 22, 15, 0);

        when(citaRepository.findById(1L)).thenReturn(Optional.of(citaProgramada));
        when(horarioMedicoRepository.findByMedicoIdAndActivoTrue(1L)).thenReturn(List.of(horario));
        when(citaRepository.countOverlapByMedico(any(), eq(EstadoCita.CANCELADA), any(), any())).thenReturn(0L);

        String result = citaService.reprogramar(1L, nuevaFecha);

        assertEquals("Cita reprogramada correctamente", result);
        assertEquals(nuevaFecha, citaProgramada.getFechaHora());
        verify(emailService, times(1)).enviarReprogramacion(any(Cita.class), any());
    }

    @Test
    void actualizar_deberiaRetornarError_CuandoCitaNoEstaProgramada() {
        citaProgramada.setEstado(EstadoCita.ATENDIDA);
        when(citaRepository.findById(1L)).thenReturn(Optional.of(citaProgramada));

        String result = citaService.actualizar(1L, citaDTO);

        assertEquals("Solo se pueden editar citas en estado PROGRAMADA", result);
    }
}
