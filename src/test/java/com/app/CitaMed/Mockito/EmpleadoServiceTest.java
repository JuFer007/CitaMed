package com.app.CitaMed.Mockito;
import com.app.CitaMed.DTO.EmpleadoDTO;
import com.app.CitaMed.Enums.Genero;
import com.app.CitaMed.Enums.Rol;
import com.app.CitaMed.Model.Administrativo.Empleado;
import com.app.CitaMed.Repository.Administrativo.EmpleadoRepository;
import com.app.CitaMed.Repository.Administrativo.UsuarioRepository;
import com.app.CitaMed.Service.Administrativo.EmpleadoService;
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
class EmpleadoServiceTest {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private EmpleadoService empleadoService;

    private EmpleadoDTO dto;

    @BeforeEach
    void setUp() {
        dto = new EmpleadoDTO();
        dto.setNombre("María");
        dto.setApellidoPaterno("Torres");
        dto.setApellidoMaterno("Ruiz");
        dto.setDni("87654321");
        dto.setTelefono("912345678");
        dto.setDireccion("Calle 456");
        dto.setEmail("maria@email.com");
        dto.setFechaNacimiento(LocalDate.of(1985, 3, 20));
        dto.setGenero(Genero.FEMENINO);
        dto.setCargo("Recepcionista");
        dto.setSalario(2500.0);
        dto.setFechaIngreso(LocalDate.now());
        dto.setUserName("maria.torres");
        dto.setPassword("pass123");
        dto.setRol(Rol.RECEPCIONISTA);
    }

    // ─── TEST 1 ───────────────────────────────────────────────────────────────
    @Test
    @DisplayName("No debe registrar empleado si el DNI ya existe")
    void save_debeRetornarError_cuandoDniYaExiste() {
        when(empleadoRepository.existsByDni("87654321")).thenReturn(true);
        String resultado = empleadoService.save(dto);
        assertThat(resultado).isEqualTo("Ya existe un empleado con ese DNI");
        verify(empleadoRepository, never()).save(any());
    }

    // ─── TEST 2 ───────────────────────────────────────────────────────────────
    @Test
    @DisplayName("No debe registrar empleado si el username ya está en uso")
    void save_debeRetornarError_cuandoUsernameYaExiste() {
        when(empleadoRepository.existsByDni("87654321")).thenReturn(false);
        when(usuarioRepository.existsByUserName("maria.torres")).thenReturn(true);
        String resultado = empleadoService.save(dto);
        assertThat(resultado).isEqualTo("El nombre de usuario ya está en uso");
        verify(empleadoRepository, never()).save(any());
    }

    // ─── TEST 3 ───────────────────────────────────────────────────────────────
    @Test
    @DisplayName("Debe registrar empleado correctamente cuando los datos son válidos")
    void save_debeRegistrarEmpleado_cuandoDatosSonValidos() {
        when(empleadoRepository.existsByDni("87654321")).thenReturn(false);
        when(usuarioRepository.existsByUserName("maria.torres")).thenReturn(false);
        String resultado = empleadoService.save(dto);
        assertThat(resultado).isEqualTo("Empleado registrado correctamente");
        verify(usuarioRepository, times(1)).save(any());
        verify(empleadoRepository, times(1)).save(any());
    }

    // ─── TEST 4 ───────────────────────────────────────────────────────────────
    @Test
    @DisplayName("No debe actualizar empleado si no existe")
    void update_debeRetornarError_cuandoEmpleadoNoExiste() {
        when(empleadoRepository.findById(99L)).thenReturn(Optional.empty());
        String resultado = empleadoService.update(99L, dto);
        assertThat(resultado).isEqualTo("Empleado no encontrado");
        verify(empleadoRepository, never()).save(any());
    }

    // ─── TEST 5 ───────────────────────────────────────────────────────────────
    @Test
    @DisplayName("Debe cambiar estado activo/inactivo del empleado")
    void toggleActivo_debeCambiarEstado() {
        Empleado empleado = new Empleado();
        empleado.setId(1L);
        empleado.setActivo(true);
        when(empleadoRepository.findById(1L)).thenReturn(Optional.of(empleado));
        String resultado = empleadoService.toggleActivo(1L);
        assertThat(resultado).isEqualTo("Empleado desactivado");
        assertThat(empleado.isActivo()).isFalse();
    }
}