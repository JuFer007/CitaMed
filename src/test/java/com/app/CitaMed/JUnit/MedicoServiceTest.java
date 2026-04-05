package com.app.CitaMed.JUnit;
import com.app.CitaMed.DTO.MedicoDTO;
import com.app.CitaMed.Enums.Genero;
import com.app.CitaMed.Model.Administrativo.Usuario;
import com.app.CitaMed.Model.Medico.Especialidad;
import com.app.CitaMed.Model.Medico.Medico;
import com.app.CitaMed.Repository.Administrativo.UsuarioRepository;
import com.app.CitaMed.Repository.Medico.EspecialidadRepository;
import com.app.CitaMed.Repository.Medico.MedicoRepository;
import com.app.CitaMed.Service.Medico.MedicoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedicoServiceTest {

    @Mock
    private MedicoRepository medicoRepository;

    @Mock
    private EspecialidadRepository especialidadRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private MedicoService medicoService;

    private MedicoDTO dto;
    private Especialidad especialidad;

    @BeforeEach
    void setUp() {
        especialidad = new Especialidad();
        especialidad.setId(1L);
        especialidad.setNombre("Cardiología");
        dto = new MedicoDTO();
        dto.setNombre("Carlos");
        dto.setApellidoPaterno("Pérez");
        dto.setApellidoMaterno("Soto");
        dto.setDni("11223344");
        dto.setTelefono("987123456");
        dto.setDireccion("Av. Salud 789");
        dto.setEmail("carlos@medico.com");
        dto.setFechaNacimiento(LocalDate.of(1980, 1, 10));
        dto.setGenero(Genero.MASCULINO);
        dto.setNumeroColegiatura("CMP-12345");
        dto.setEspecialidadId(1L);
        dto.setUserName("carlos.perez");
        dto.setPassword("pass123");
    }

    // ─── TEST 1 ───────────────────────────────────────────────────────────────
    @Test
    @DisplayName("No debe registrar médico si el DNI ya existe")
    void save_debeRetornarError_cuandoDniYaExiste() {
        when(medicoRepository.existsByDni("11223344")).thenReturn(true);
        String resultado = medicoService.save(dto);
        assertThat(resultado).isEqualTo("Ya existe un médico con ese DNI");
        verify(medicoRepository, never()).save(any());
    }

    // ─── TEST 2 ───────────────────────────────────────────────────────────────
    @Test
    @DisplayName("No debe registrar médico si el username ya está en uso")
    void save_debeRetornarError_cuandoUsernameYaExiste() {
        when(medicoRepository.existsByDni("11223344")).thenReturn(false);
        when(usuarioRepository.existsByUserName("carlos.perez")).thenReturn(true);
        String resultado = medicoService.save(dto);
        assertThat(resultado).isEqualTo("El nombre de usuario ya está en uso");
        verify(medicoRepository, never()).save(any());
    }

    // ─── TEST 3 ───────────────────────────────────────────────────────────────
    @Test
    @DisplayName("No debe registrar médico si la especialidad no existe")
    void save_debeRetornarError_cuandoEspecialidadNoExiste() {
        when(medicoRepository.existsByDni("11223344")).thenReturn(false);
        when(usuarioRepository.existsByUserName("carlos.perez")).thenReturn(false);
        when(especialidadRepository.findById(1L)).thenReturn(Optional.empty());
        String resultado = medicoService.save(dto);
        assertThat(resultado).isEqualTo("Especialidad no encontrada");
        verify(medicoRepository, never()).save(any());
    }

    // ─── TEST 4 ───────────────────────────────────────────────────────────────
    @Test
    @DisplayName("Debe registrar médico correctamente cuando todos los datos son válidos")
    void save_debeRegistrarMedico_cuandoDatosSonValidos() {
        when(medicoRepository.existsByDni("11223344")).thenReturn(false);
        when(usuarioRepository.existsByUserName("carlos.perez")).thenReturn(false);
        when(especialidadRepository.findById(1L)).thenReturn(Optional.of(especialidad));
        String resultado = medicoService.save(dto);
        assertThat(resultado).isEqualTo("Médico registrado correctamente");
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
        verify(medicoRepository, times(1)).save(any(Medico.class));
    }

    // ─── TEST 5 ───────────────────────────────────────────────────────────────
    @Test
    @DisplayName("Debe activar o desactivar el médico correctamente")
    void toggleActivo_debeCambiarEstadoMedico() {
        Medico medico = new Medico();
        medico.setId(1L);
        medico.setActivo(true);
        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));
        String resultado = medicoService.toggleActivo(1L);
        assertThat(resultado).isEqualTo("Médico desactivado");
        assertThat(medico.isActivo()).isFalse();
        verify(medicoRepository, times(1)).save(medico);
    }

    // ─── TEST 6 ───────────────────────────────────────────────────────────────
    @Test
    @DisplayName("No debe cambiar especialidad si el médico no existe")
    void updateEspecialidad_debeRetornarError_cuandoMedicoNoExiste() {
        when(medicoRepository.findById(99L)).thenReturn(Optional.empty());
        String resultado = medicoService.updateEspecialidad(99L, 1L);
        assertThat(resultado).isEqualTo("Médico no encontrado");
        verify(medicoRepository, never()).save(any());
    }
}
