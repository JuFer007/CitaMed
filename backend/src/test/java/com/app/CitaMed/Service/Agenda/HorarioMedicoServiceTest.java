package com.app.CitaMed.Service.Agenda;

import com.app.CitaMed.DTO.HorarioMedicoDTO;
import com.app.CitaMed.Enums.DiaSemana;
import com.app.CitaMed.Model.Administrativo.Consultorio;
import com.app.CitaMed.Model.Agenda.HorarioMedico;
import com.app.CitaMed.Model.Medico.Especialidad;
import com.app.CitaMed.Model.Medico.Medico;
import com.app.CitaMed.Repository.Administrativo.ConsultorioRepository;
import com.app.CitaMed.Repository.Agenda.HorarioMedicoRepository;
import com.app.CitaMed.Repository.Medico.MedicoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HorarioMedicoServiceTest {

    @Mock private HorarioMedicoRepository horarioMedicoRepository;
    @Mock private MedicoRepository medicoRepository;
    @Mock private ConsultorioRepository consultorioRepository;

    @InjectMocks
    private HorarioMedicoService horarioMedicoService;

    private Medico medico;
    private Consultorio consultorio;
    private HorarioMedicoDTO horarioDTO;
    private HorarioMedico horario;

    @BeforeEach
    void setUp() {
        Especialidad especialidad = new Especialidad(1L, "Cardiología", null);
        consultorio = new Consultorio(1L, "101", "Principal", true, especialidad);
        medico = new Medico();
        medico.setId(1L);
        medico.setNombre("Dr. Carlos");
        medico.setApellidoPaterno("Lopez");

        horarioDTO = new HorarioMedicoDTO();
        horarioDTO.setMedicoId(1L);
        horarioDTO.setConsultorioId(1L);
        horarioDTO.setDia(DiaSemana.LUNES);
        horarioDTO.setHoraInicio(LocalTime.of(8, 0));
        horarioDTO.setHoraFin(LocalTime.of(12, 0));

        horario = new HorarioMedico();
        horario.setId(1L);
        horario.setMedico(medico);
        horario.setConsultorio(consultorio);
        horario.setDia(DiaSemana.LUNES);
        horario.setHoraInicio(LocalTime.of(8, 0));
        horario.setHoraFin(LocalTime.of(12, 0));
        horario.setActivo(true);
    }

    @Test
    void save_deberiaRetornarError_CuandoMedicoNoExiste() {
        when(medicoRepository.findById(1L)).thenReturn(Optional.empty());

        String result = horarioMedicoService.save(horarioDTO);

        assertEquals("Médico no encontrado", result);
    }

    @Test
    void save_deberiaRetornarError_CuandoConsultorioNoExiste() {
        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));
        when(consultorioRepository.findById(1L)).thenReturn(Optional.empty());

        String result = horarioMedicoService.save(horarioDTO);

        assertEquals("Consultorio no encontrado", result);
    }

    @Test
    void save_deberiaRetornarError_CuandoHayCruceDeHorarios() {
        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));
        when(consultorioRepository.findById(1L)).thenReturn(Optional.of(consultorio));
        when(horarioMedicoRepository.existsByMedicoIdAndDiaAndActivoTrueAndHoraInicioLessThanAndHoraFinGreaterThan(
                1L, DiaSemana.LUNES, LocalTime.of(12, 0), LocalTime.of(8, 0)
        )).thenReturn(true);

        String result = horarioMedicoService.save(horarioDTO);

        assertEquals("El médico ya tiene un horario que se cruza en ese día y hora", result);
    }

    @Test
    void save_deberiaRegistrarHorario_CuandoTodoEsValido() {
        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));
        when(consultorioRepository.findById(1L)).thenReturn(Optional.of(consultorio));
        when(horarioMedicoRepository.existsByMedicoIdAndDiaAndActivoTrueAndHoraInicioLessThanAndHoraFinGreaterThan(
                any(), any(), any(), any()
        )).thenReturn(false);

        String result = horarioMedicoService.save(horarioDTO);

        assertEquals("Horario registrado correctamente", result);
        verify(horarioMedicoRepository, times(1)).save(any(HorarioMedico.class));
    }

    @Test
    void toggleActivo_deberiaActivar_CuandoEstabaInactivo() {
        horario.setActivo(false);
        when(horarioMedicoRepository.findById(1L)).thenReturn(Optional.of(horario));

        String result = horarioMedicoService.toggleActivo(1L);

        assertEquals("Horario activado", result);
        assertTrue(horario.isActivo());
    }

    @Test
    void toggleActivo_deberiaDesactivar_CuandoEstabaActivo() {
        when(horarioMedicoRepository.findById(1L)).thenReturn(Optional.of(horario));

        String result = horarioMedicoService.toggleActivo(1L);

        assertEquals("Horario desactivado", result);
        assertFalse(horario.isActivo());
    }

    @Test
    void toggleActivo_deberiaRetornarError_CuandoHorarioNoExiste() {
        when(horarioMedicoRepository.findById(99L)).thenReturn(Optional.empty());

        String result = horarioMedicoService.toggleActivo(99L);

        assertEquals("Horario no encontrado", result);
    }

    @Test
    void update_deberiaActualizarHorario_CuandoTodoEsValido() {
        when(horarioMedicoRepository.findById(1L)).thenReturn(Optional.of(horario));
        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));
        when(consultorioRepository.findById(1L)).thenReturn(Optional.of(consultorio));

        String result = horarioMedicoService.update(1L, horarioDTO);

        assertEquals("Horario actualizado correctamente", result);
        verify(horarioMedicoRepository, times(1)).save(any(HorarioMedico.class));
    }

    @Test
    void findByMedico_deberiaRetornarHorarios() {
        when(horarioMedicoRepository.findByMedicoIdAndActivoTrue(1L)).thenReturn(List.of(horario));

        List<HorarioMedico> result = horarioMedicoService.findByMedico(1L);

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void findAll_deberiaRetornarTodosLosHorarios() {
        when(horarioMedicoRepository.findAll()).thenReturn(List.of(horario));

        List<HorarioMedico> result = horarioMedicoService.findAll();

        assertFalse(result.isEmpty());
    }
}
