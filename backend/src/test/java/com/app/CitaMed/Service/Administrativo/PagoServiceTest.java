package com.app.CitaMed.Service.Administrativo;

import com.app.CitaMed.DTO.PagoDTO;
import com.app.CitaMed.Enums.EstadoCita;
import com.app.CitaMed.Enums.EstadoPago;
import com.app.CitaMed.Enums.MetodoPago;
import com.app.CitaMed.Model.Administrativo.Pago;
import com.app.CitaMed.Model.Agenda.Cita;
import com.app.CitaMed.Repository.Administrativo.PagoRepository;
import com.app.CitaMed.Repository.Agenda.CitaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PagoServiceTest {

    @Mock private PagoRepository pagoRepository;
    @Mock private CitaRepository citaRepository;

    @InjectMocks
    private PagoService pagoService;

    private Cita citaProgramada;
    private PagoDTO pagoDTO;
    private Pago pago;

    @BeforeEach
    void setUp() {
        citaProgramada = new Cita();
        citaProgramada.setId(1L);
        citaProgramada.setEstado(EstadoCita.PROGRAMADA);

        pagoDTO = new PagoDTO();
        pagoDTO.setCitaId(1L);
        pagoDTO.setMonto(80.00);
        pagoDTO.setMetodoPago(MetodoPago.EFECTIVO);

        pago = new Pago();
        pago.setId(1L);
        pago.setCita(citaProgramada);
        pago.setMonto(80.00);
        pago.setMetodoPago(MetodoPago.EFECTIVO);
        pago.setEstado(EstadoPago.PAGADO);
    }

    @Test
    void save_deberiaRetornarError_CuandoCitaNoExiste() {
        when(citaRepository.findById(1L)).thenReturn(Optional.empty());

        String result = pagoService.save(pagoDTO);

        assertEquals("Cita no encontrada", result);
    }

    @Test
    void save_deberiaRetornarError_CuandoCitaYaTienePago() {
        when(citaRepository.findById(1L)).thenReturn(Optional.of(citaProgramada));
        when(pagoRepository.existsByCitaId(1L)).thenReturn(true);

        String result = pagoService.save(pagoDTO);

        assertEquals("Esta cita ya tiene un pago registrado", result);
    }

    @Test
    void save_deberiaRetornarError_CuandoCitaEstaCancelada() {
        citaProgramada.setEstado(EstadoCita.CANCELADA);
        when(citaRepository.findById(1L)).thenReturn(Optional.of(citaProgramada));

        String result = pagoService.save(pagoDTO);

        assertEquals("No se puede registrar pago de una cita cancelada", result);
    }

    @Test
    void save_deberiaRegistrarPago_CuandoTodoEsValido() {
        when(citaRepository.findById(1L)).thenReturn(Optional.of(citaProgramada));
        when(pagoRepository.existsByCitaId(1L)).thenReturn(false);

        String result = pagoService.save(pagoDTO);

        assertEquals("Pago registrado correctamente", result);
        verify(pagoRepository, times(1)).save(any(Pago.class));
    }

    @Test
    void anular_deberiaAnularPago() {
        when(pagoRepository.findById(1L)).thenReturn(Optional.of(pago));

        String result = pagoService.anular(1L);

        assertEquals("Pago anulado correctamente", result);
        assertEquals(EstadoPago.ANULADO, pago.getEstado());
    }

    @Test
    void anular_deberiaRetornarError_CuandoPagoNoExiste() {
        when(pagoRepository.findById(99L)).thenReturn(Optional.empty());

        String result = pagoService.anular(99L);

        assertEquals("Pago no encontrado", result);
    }

    @Test
    void findAll_deberiaRetornarListaDePagos() {
        when(pagoRepository.findAll()).thenReturn(List.of(pago));

        List<Pago> result = pagoService.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void findById_deberiaRetornarPago_CuandoExiste() {
        when(pagoRepository.findById(1L)).thenReturn(Optional.of(pago));

        Pago result = pagoService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void findById_deberiaRetornarNull_CuandoNoExiste() {
        when(pagoRepository.findById(99L)).thenReturn(Optional.empty());

        assertNull(pagoService.findById(99L));
    }
}
