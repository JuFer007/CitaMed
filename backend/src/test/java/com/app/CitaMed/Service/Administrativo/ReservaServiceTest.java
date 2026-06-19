package com.app.CitaMed.Service.Administrativo;

import com.app.CitaMed.DTO.ReservaDTO;
import com.app.CitaMed.DTO.SlotDisponibleDTO;
import com.app.CitaMed.Enums.*;
import com.app.CitaMed.Model.Administrativo.Consultorio;
import com.app.CitaMed.Model.Administrativo.Pago;
import com.app.CitaMed.Model.Agenda.Cita;
import com.app.CitaMed.Model.Agenda.HorarioMedico;
import com.app.CitaMed.Model.Medico.Especialidad;
import com.app.CitaMed.Model.Medico.Medico;
import com.app.CitaMed.Model.Paciente.Paciente;
import com.app.CitaMed.Repository.Administrativo.ConsultorioRepository;
import com.app.CitaMed.Repository.Administrativo.PagoRepository;
import com.app.CitaMed.Repository.Agenda.CitaRepository;
import com.app.CitaMed.Repository.Agenda.HorarioMedicoRepository;
import com.app.CitaMed.Repository.Medico.MedicoRepository;
import com.app.CitaMed.Repository.Paciente.HistorialMedicoRepository;
import com.app.CitaMed.Repository.Paciente.PacienteRepository;
import com.app.CitaMed.Service.MicroServicios.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservaServiceTest {

    @Mock private CitaRepository citaRepository;
    @Mock private PagoRepository pagoRepository;
    @Mock private PacienteRepository pacienteRepository;
    @Mock private HistorialMedicoRepository historialMedicoRepository;
    @Mock private MedicoRepository medicoRepository;
    @Mock private ConsultorioRepository consultorioRepository;
    @Mock private HorarioMedicoRepository horarioMedicoRepository;
    @Mock private EmailService emailService;

    @InjectMocks
    private ReservaService reservaService;

    private Medico medico;
    private Consultorio consultorio;
    private HorarioMedico horario;
    private Paciente pacienteExistente;
    private ReservaDTO reservaDTO;
    private Especialidad especialidad;

    @BeforeEach
    void setUp() {
        especialidad = new Especialidad(1L, "Cardiología", null);
        consultorio = new Consultorio(1L, "101", "Principal", true, especialidad);

        medico = new Medico();
        medico.setId(1L);
        medico.setNombre("Dr. Carlos");
        medico.setApellidoPaterno("Lopez");
        medico.setGenero(Genero.MASCULINO);
        medico.setActivo(true);
        medico.setConsultorio(consultorio);
        medico.setEspecialidad(especialidad);

        horario = new HorarioMedico();
        horario.setId(1L);
        horario.setMedico(medico);
        horario.setConsultorio(consultorio);
        horario.setDia(DiaSemana.LUNES);
        horario.setHoraInicio(LocalTime.of(8, 0));
        horario.setHoraFin(LocalTime.of(12, 0));
        horario.setActivo(true);

        pacienteExistente = new Paciente();
        pacienteExistente.setId(1L);
        pacienteExistente.setNombre("JUAN");
        pacienteExistente.setApellidoPaterno("PEREZ");
        pacienteExistente.setDni("12345678");
        pacienteExistente.setActivo(true);

        reservaDTO = new ReservaDTO();
        reservaDTO.setDni("12345678");
        reservaDTO.setNombre("Juan");
        reservaDTO.setApellidoPaterno("Perez");
        reservaDTO.setApellidoMaterno("Garcia");
        reservaDTO.setTelefono("987654321");
        reservaDTO.setEmail("juan@email.com");
        reservaDTO.setDireccion("Av. Principal 123");
        reservaDTO.setFechaNacimiento(LocalDate.of(1990, 1, 1));
        reservaDTO.setGenero("MASCULINO");
        reservaDTO.setGrupoSanguineo("O_POSITIVO");
        reservaDTO.setMedicoId(1L);
        reservaDTO.setConsultorioId(1L);
        reservaDTO.setFechaHora("2026-06-22T10:00:00");
        reservaDTO.setMotivoConsulta("Control cardíaco");
    }

    @Test
    void obtenerSlotsDisponibles_deberiaRetornarListaVacia_CuandoDiaNoValido() {
        List<SlotDisponibleDTO> result = reservaService.obtenerSlotsDisponibles(1L, "2026-06-21");

        assertTrue(result.isEmpty());
    }

    @Test
    void obtenerSlotsDisponibles_deberiaRetornarListaVacia_CuandoNoHayMedicos() {
        when(medicoRepository.findByEspecialidadIdAndActivoTrue(1L)).thenReturn(List.of());

        List<SlotDisponibleDTO> result = reservaService.obtenerSlotsDisponibles(1L, "2026-06-22");

        assertTrue(result.isEmpty());
    }

    @Test
    void obtenerSlotsDisponibles_deberiaRetornarSlots_CuandoHayDisponibilidad() {
        when(medicoRepository.findByEspecialidadIdAndActivoTrue(1L)).thenReturn(List.of(medico));
        when(horarioMedicoRepository.findByMedicoIdInAndDiaAndActivoTrue(
                List.of(1L), DiaSemana.LUNES)).thenReturn(List.of(horario));
        when(citaRepository.findByMedicoIdAndFechaHoraBetweenAndEstadoNot(
                any(), any(), any(), eq(EstadoCita.CANCELADA))).thenReturn(List.of());

        List<SlotDisponibleDTO> result = reservaService.obtenerSlotsDisponibles(1L, "2026-06-22");

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getMedicoId());
        assertFalse(result.get(0).getHorasDisponibles().isEmpty());
    }

    @Test
    void procesarReserva_deberiaReservar_CuandoPacienteYaExiste() {
        when(pacienteRepository.findByDniAndActivoTrue("12345678")).thenReturn(pacienteExistente);
        when(medicoRepository.findById(1L)).thenReturn(java.util.Optional.of(medico));
        when(citaRepository.countOverlapByMedico(any(), eq(EstadoCita.CANCELADA), any(), any())).thenReturn(0L);
        when(citaRepository.save(any(Cita.class))).thenAnswer(i -> i.getArgument(0));
        when(pagoRepository.save(any(Pago.class))).thenAnswer(i -> i.getArgument(0));

        String result = reservaService.procesarReserva(reservaDTO);

        assertEquals("Reserva registrada correctamente", result);
        verify(citaRepository, times(1)).save(any(Cita.class));
        verify(pagoRepository, times(1)).save(any(Pago.class));
        verify(emailService, times(1)).enviarConfirmacion(any(Cita.class));
    }

    @Test
    void procesarReserva_deberiaCrearNuevoPaciente_CuandoNoExiste() {
        when(pacienteRepository.findByDniAndActivoTrue("12345678")).thenReturn(null);
        when(pacienteRepository.findByDni("12345678")).thenReturn(null);
        when(medicoRepository.findById(1L)).thenReturn(java.util.Optional.of(medico));
        when(citaRepository.countOverlapByMedico(any(), eq(EstadoCita.CANCELADA), any(), any())).thenReturn(0L);
        when(citaRepository.save(any(Cita.class))).thenAnswer(i -> i.getArgument(0));
        when(pagoRepository.save(any(Pago.class))).thenAnswer(i -> i.getArgument(0));
        when(pacienteRepository.save(any(Paciente.class))).thenAnswer(i -> i.getArgument(0));

        String result = reservaService.procesarReserva(reservaDTO);

        assertEquals("Reserva registrada correctamente", result);
        verify(pacienteRepository, times(1)).save(any(Paciente.class));
        verify(historialMedicoRepository, times(1)).save(any());
    }

    @Test
    void procesarReserva_deberiaLanzarExcepcion_CuandoMedicoNoExiste() {
        when(pacienteRepository.findByDniAndActivoTrue("12345678")).thenReturn(pacienteExistente);
        when(medicoRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        assertThrows(RuntimeException.class, () -> reservaService.procesarReserva(reservaDTO));
    }

    @Test
    void procesarReserva_deberiaLanzarExcepcion_CuandoMedicoNoEstaActivo() {
        medico.setActivo(false);
        when(pacienteRepository.findByDniAndActivoTrue("12345678")).thenReturn(pacienteExistente);
        when(medicoRepository.findById(1L)).thenReturn(java.util.Optional.of(medico));

        assertThrows(RuntimeException.class, () -> reservaService.procesarReserva(reservaDTO));
    }

    @Test
    void procesarReserva_deberiaLanzarExcepcion_CuandoMedicoNoTieneConsultorio() {
        medico.setConsultorio(null);
        when(pacienteRepository.findByDniAndActivoTrue("12345678")).thenReturn(pacienteExistente);
        when(medicoRepository.findById(1L)).thenReturn(java.util.Optional.of(medico));

        assertThrows(RuntimeException.class, () -> reservaService.procesarReserva(reservaDTO));
    }
}
