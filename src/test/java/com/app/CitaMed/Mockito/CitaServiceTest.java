package com.app.CitaMed.Mockito;
import com.app.CitaMed.DTO.CitaDTO;
import com.app.CitaMed.Enums.EstadoCita;
import com.app.CitaMed.Model.Administrativo.Consultorio;
import com.app.CitaMed.Model.Agenda.Cita;
import com.app.CitaMed.Model.Medico.Medico;
import com.app.CitaMed.Model.Paciente.Paciente;
import com.app.CitaMed.Repository.Administrativo.ConsultorioRepository;
import com.app.CitaMed.Repository.Agenda.CitaRepository;
import com.app.CitaMed.Repository.Medico.MedicoRepository;
import com.app.CitaMed.Repository.Paciente.PacienteRepository;
import com.app.CitaMed.Service.Agenda.CitaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class CitaServiceTest {
    @Mock
    private CitaRepository citaRepository;
    @Mock
    private PacienteRepository pacienteRepository;
    @Mock
    private MedicoRepository medicoRepository;
    @Mock
    private ConsultorioRepository consultorioRepository;

    @InjectMocks
    private CitaService citaService;

    private CitaDTO dto;
    private Paciente paciente;
    private Medico medico;
    private Consultorio consultorio;

    @BeforeEach
    void setUp() {
        dto = new CitaDTO();
        dto.setPacienteId(1L);
        dto.setMedicoId(1L);
        dto.setConsultorioId(1L);
        dto.setFechaHora(LocalDateTime.now().plusDays(1));
        dto.setMotivoConsulta("Dolor de cabeza");
        paciente = new Paciente();
        paciente.setId(1L);
        medico = new Medico();
        medico.setId(1L);
        medico.setActivo(true);
        consultorio = new Consultorio();
        consultorio.setId(1L);
        consultorio.setDisponible(true);
    }

    // ─── TEST 1 ───────────────────────────────────────────────────────────────
    @Test
    @DisplayName("No debe registrar una cita si el paciente no existe")
    void save_debeRetornarError_cuandoPacienteNoExiste() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.empty());
        String resultado = citaService.save(dto);
        assertThat(resultado).isEqualTo("Paciente no encontrado");
        verify(citaRepository, never()).save(any());
    }

    // ─── TEST 2 ───────────────────────────────────────────────────────────────
    @Test
    @DisplayName("No debe registrar una cita si el médico no existe")
    void save_debeRetornarError_cuandoMedicoNoExiste() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(medicoRepository.findById(1L)).thenReturn(Optional.empty());
        String resultado = citaService.save(dto);
        assertThat(resultado).isEqualTo("Médico no encontrado");
        verify(citaRepository, never()).save(any());
    }

    // ─── TEST 3 ───────────────────────────────────────────────────────────────
    @Test
    @DisplayName("No debe registrar una cita si el médico está inactivo")
    void save_debeRetornarError_cuandoMedicoInactivo() {
        medico.setActivo(false);
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));
        String resultado = citaService.save(dto);
        assertThat(resultado).isEqualTo("El médico no está activo");
        verify(citaRepository, never()).save(any());
    }

    // ─── TEST 4 ───────────────────────────────────────────────────────────────
    @Test
    @DisplayName("No debe registrar una cita si el consultorio no está disponible")
    void save_debeRetornarError_cuandoConsultorioNoDisponible() {
        consultorio.setDisponible(false);
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));
        when(consultorioRepository.findById(1L)).thenReturn(Optional.of(consultorio));
        String resultado = citaService.save(dto);
        assertThat(resultado).isEqualTo("El consultorio no está disponible");
        verify(citaRepository, never()).save(any());
    }

    // ─── TEST 5 ───────────────────────────────────────────────────────────────
    @Test
    @DisplayName("No debe registrar cita si el médico ya tiene una cita en ese horario")
    void save_debeRetornarError_cuandoMedicoTieneCitaEnEseHorario() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));
        when(consultorioRepository.findById(1L)).thenReturn(Optional.of(consultorio));
        when(citaRepository.existsByMedicoIdAndFechaHoraAndEstadoNot(1L, dto.getFechaHora(), EstadoCita.CANCELADA)).thenReturn(true);
        String resultado = citaService.save(dto);
        assertThat(resultado).isEqualTo("El médico ya tiene una cita en ese horario");
        verify(citaRepository, never()).save(any());
    }

    // ─── TEST 6 ───────────────────────────────────────────────────────────────
    @Test
    @DisplayName("Debe registrar la cita correctamente cuando todos los datos son válidos")
    void save_debeRegistrarCita_cuandoDatosSonValidos() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));
        when(consultorioRepository.findById(1L)).thenReturn(Optional.of(consultorio));
        when(citaRepository.existsByMedicoIdAndFechaHoraAndEstadoNot(any(), any(), any())).thenReturn(false);
        when(citaRepository.existsByPacienteIdAndFechaHoraAndEstadoNot(any(), any(), any())).thenReturn(false);
        String resultado = citaService.save(dto);
        assertThat(resultado).isEqualTo("Cita registrada correctamente");
        verify(citaRepository, times(1)).save(any(Cita.class));
    }

    // ─── TEST 7 ───────────────────────────────────────────────────────────────
    @Test
    @DisplayName("No debe cancelar una cita que ya fue atendida")
    void cancelar_debeRetornarError_cuandoCitaYaAtendida() {
        Cita cita = new Cita();
        cita.setId(1L);
        cita.setEstado(EstadoCita.ATENDIDA);
        when(citaRepository.findById(1L)).thenReturn(Optional.of(cita));
        String resultado = citaService.cancelar(1L);
        assertThat(resultado).isEqualTo("No se puede cancelar una cita ya atendida");
        verify(citaRepository, never()).save(any());
    }
}
