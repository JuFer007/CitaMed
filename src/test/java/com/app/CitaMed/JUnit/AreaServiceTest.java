package com.app.CitaMed.JUnit;
import com.app.CitaMed.Model.Administrativo.Area;
import com.app.CitaMed.Model.Administrativo.Consultorio;
import com.app.CitaMed.Repository.Administrativo.AreaRepository;
import com.app.CitaMed.Service.Administrativo.AreaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AreaServiceTest {

    @Mock
    private AreaRepository areaRepository;

    @InjectMocks
    private AreaService areaService;

    private Area area;

    @BeforeEach
    void setUp() {
        area = new Area();
        area.setId(1L);
        area.setNombre("CARDIOLOGÍA");
        area.setDescripcion("Área de cardiología");
        area.setConsultorios(new ArrayList<>());
    }

    // ─── TEST 1 ───────────────────────────────────────────────────────────────
    @Test
    @DisplayName("No debe registrar un área si el nombre ya existe")
    void save_debeRetornarError_cuandoNombreYaExiste() {
        when(areaRepository.existsByNombre("CARDIOLOGÍA")).thenReturn(true);
        String resultado = areaService.save(area);
        assertThat(resultado).isEqualTo("Ya existe un área con ese nombre");
        verify(areaRepository, never()).save(any());
    }

    // ─── TEST 2 ───────────────────────────────────────────────────────────────
    @Test
    @DisplayName("Debe registrar el área correctamente cuando el nombre no existe")
    void save_debeRegistrarArea_cuandoNombreNoExiste() {
        when(areaRepository.existsByNombre("CARDIOLOGÍA")).thenReturn(false);
        String resultado = areaService.save(area);
        assertThat(resultado).isEqualTo("Área registrada correctamente");
        verify(areaRepository, times(1)).save(area);
    }

    // ─── TEST 3 ───────────────────────────────────────────────────────────────
    @Test
    @DisplayName("No debe eliminar un área si tiene consultorios asignados")
    void delete_debeRetornarError_cuandoAreaTieneConsultorios() {
        Consultorio consultorio = new Consultorio();
        area.setConsultorios(Arrays.asList(consultorio));
        when(areaRepository.findById(1L)).thenReturn(Optional.of(area));
        String resultado = areaService.delete(1L);
        assertThat(resultado).isEqualTo("No se puede eliminar un área que tiene consultorios asignados");
        verify(areaRepository, never()).deleteById(any());
    }

    // ─── TEST 4 ───────────────────────────────────────────────────────────────
    @Test
    @DisplayName("Debe eliminar el área correctamente cuando no tiene consultorios")
    void delete_debeEliminarArea_cuandoNoTieneConsultorios() {
        when(areaRepository.findById(1L)).thenReturn(Optional.of(area));
        String resultado = areaService.delete(1L);
        assertThat(resultado).isEqualTo("Área eliminada correctamente");
        verify(areaRepository, times(1)).deleteById(1L);
    }

    // ─── TEST 5 ───────────────────────────────────────────────────────────────
    @Test
    @DisplayName("No debe actualizar un área si no existe")
    void update_debeRetornarError_cuandoAreaNoExiste() {
        when(areaRepository.findById(99L)).thenReturn(Optional.empty());
        String resultado = areaService.update(99L, area);
        assertThat(resultado).isEqualTo("Área no encontrada");
        verify(areaRepository, never()).save(any());
    }

    // ─── TEST 6 ───────────────────────────────────────────────────────────────
    @Test
    @DisplayName("Debe retornar todas las áreas registradas")
    void findAll_debeRetornarListaDeAreas() {
        when(areaRepository.findAll()).thenReturn(Arrays.asList(area));
        List<Area> resultado = areaService.findAll();
        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getNombre()).isEqualTo("CARDIOLOGÍA");
    }
}
