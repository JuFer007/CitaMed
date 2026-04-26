package com.app.CitaMed.Mockito;
import com.app.CitaMed.DTO.PacienteDTO;
import com.app.CitaMed.Enums.Genero;
import com.app.CitaMed.Enums.GrupoSanguineo;
import com.app.CitaMed.Model.Paciente.Paciente;
import com.app.CitaMed.Repository.Paciente.HistorialMedicoRepository;
import com.app.CitaMed.Repository.Paciente.PacienteRepository;
import com.app.CitaMed.Service.Paciente.PacienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class PacienteServiceTest {
    @Mock
    private PacienteRepository pacienteRepository;

    @Mock
    private HistorialMedicoRepository historialMedicoRepository;

    @InjectMocks
    private PacienteService pacienteService;

    private PacienteDTO dto;
    private Paciente paciente;

    @BeforeEach
    void setUp() {
        dto = new PacienteDTO();
        dto.setNombre("Juan");
        dto.setApellidoPaterno("García");
        dto.setApellidoMaterno("López");
        dto.setDni("12345678");
        dto.setTelefono("987654321");
        dto.setDireccion("Av. Lima 123");
        dto.setEmail("juan@email.com");
        dto.setFechaNacimiento(LocalDate.of(1990, 5, 15));
        dto.setGenero(Genero.MASCULINO);
        dto.setGrupoSanguineo(GrupoSanguineo.O_POSITIVO);

        paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNombre("JUAN");
        paciente.setDni("12345678");
    }

    // ─── TEST 1 ───────────────────────────────────────────────────────────────
    @Test
    @DisplayName("Debe registrar un paciente correctamente cuando el DNI no existe")
    void save_debeRegistrarPaciente_cuandoDniNoExiste() {
        when(pacienteRepository.existsByDni("12345678")).thenReturn(false);
        when(pacienteRepository.save(any())).thenReturn(paciente);
        String resultado = pacienteService.save(dto);
        assertThat(resultado).isEqualTo("Paciente registrado correctamente");
        verify(pacienteRepository, times(1)).save(any(Paciente.class));
        verify(historialMedicoRepository, times(1)).save(any());
    }

    // ─── TEST 2 ───────────────────────────────────────────────────────────────
    @Test
    @DisplayName("No debe registrar un paciente si el DNI ya está registrado")
    void save_debeRetornarError_cuandoDniYaExiste() {
        when(pacienteRepository.existsByDni("12345678")).thenReturn(true);
        String resultado = pacienteService.save(dto);
        assertThat(resultado).isEqualTo("Ya existe un paciente con ese DNI");
        verify(pacienteRepository, never()).save(any());
        verify(historialMedicoRepository, never()).save(any());
    }

    // ─── TEST 3 ───────────────────────────────────────────────────────────────
    @Test
    @DisplayName("No debe actualizar un paciente si el ID no existe")
    void update_debeRetornarError_cuandoPacienteNoExiste() {
        when(pacienteRepository.findById(99L)).thenReturn(Optional.empty());
        String resultado = pacienteService.update(99L, dto);
        assertThat(resultado).isEqualTo("Paciente no encontrado");
        verify(pacienteRepository, never()).save(any());
    }

    // ─── TEST 4 ───────────────────────────────────────────────────────────────
    @Test
    @DisplayName("Debe actualizar un paciente correctamente cuando existe")
    void update_debeActualizarPaciente_cuandoExiste() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        String resultado = pacienteService.update(1L, dto);
        assertThat(resultado).isEqualTo("Paciente actualizado correctamente");
        verify(pacienteRepository, times(1)).save(paciente);
    }

    // ─── TEST 5 ───────────────────────────────────────────────────────────────
    @Test
    @DisplayName("Debe retornar todos los pacientes registrados")
    void findAll_debeRetornarListaDePacientes() {
        when(pacienteRepository.findAll()).thenReturn(Arrays.asList(paciente));
        List<Paciente> resultado = pacienteService.findAll();
        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getDni()).isEqualTo("12345678");
    }
}
