package com.app.CitaMed;

import com.app.CitaMed.DTO.EmpleadoDTO;
import com.app.CitaMed.Enums.Genero;
import com.app.CitaMed.Enums.Rol;
import com.app.CitaMed.Model.Administrativo.Empleado;
import com.app.CitaMed.Model.Administrativo.Usuario;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmpleadoServiceTest {
    @Mock
    private EmpleadoRepository empleadoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private EmpleadoService empleadoService;

    private EmpleadoDTO empleadoDTO;

    @BeforeEach
    void setUp() {
        // Configuramos un DTO de prueba estándar
        empleadoDTO = new EmpleadoDTO();
        empleadoDTO.setNombre("Juan");
        empleadoDTO.setApellidoPaterno("Perez");
        empleadoDTO.setApellidoMaterno("Sosa");
        empleadoDTO.setDni("12345678");
        empleadoDTO.setTelefono("987654321");
        empleadoDTO.setDireccion("Av. Siempre Viva 123");
        empleadoDTO.setEmail("juanperez@docCenter.com");
        empleadoDTO.setFechaNacimiento(LocalDate.of(1995, 5, 20));
        empleadoDTO.setGenero(Genero.MASCULINO);
        empleadoDTO.setCargo("Administrador");
        empleadoDTO.setSalario(2500.00);
        empleadoDTO.setFechaIngreso(LocalDate.now());
        empleadoDTO.setUserName("jperez");
        empleadoDTO.setPassword("12345");
        empleadoDTO.setRol(Rol.MEDICO);
    }

    @Test
    @DisplayName("Debe registrar un empleado exitosamente")
    void saveSuccess() {
        // Arrange: Definimos que NO existe el DNI ni el Usuario
        when(empleadoRepository.existsByDni(anyString())).thenReturn(false);
        when(usuarioRepository.existsByUserName(anyString())).thenReturn(false);

        // Act: Llamamos al método
        String resultado = empleadoService.save(empleadoDTO);

        // Assert: Verificamos resultados y que se llamaron a los save
        assertEquals("Empleado registrado correctamente", resultado);
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
        verify(empleadoRepository, times(1)).save(any(Empleado.class));
    }

    @Test
    @DisplayName("Debe fallar si el DNI ya existe")
    void saveFailDniExists() {
        // Arrange
        when(empleadoRepository.existsByDni("12345678")).thenReturn(true);

        // Act
        String resultado = empleadoService.save(empleadoDTO);

        // Assert
        assertEquals("Ya existe un empleado con ese DNI", resultado);
        // Verificamos que NUNCA se intentó guardar nada
        verify(usuarioRepository, never()).save(any());
        verify(empleadoRepository, never()).save(any());
    }

    @Test
    @DisplayName("Debe fallar si el nombre de usuario ya está en uso")
    void saveFailUserExists() {
        // Arrange
        when(empleadoRepository.existsByDni(anyString())).thenReturn(false);
        when(usuarioRepository.existsByUserName("jperez")).thenReturn(true);

        // Act
        String resultado = empleadoService.save(empleadoDTO);

        // Assert
        assertEquals("El nombre de usuario ya está en uso", resultado);
        verify(empleadoRepository, never()).save(any());
    }
}
