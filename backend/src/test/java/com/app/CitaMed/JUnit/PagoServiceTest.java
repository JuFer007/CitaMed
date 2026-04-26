package com.app.CitaMed.JUnit;
import com.app.CitaMed.DTO.PagoDTO;
import com.app.CitaMed.Enums.EstadoCita;
import com.app.CitaMed.Enums.EstadoPago;
import com.app.CitaMed.Enums.MetodoPago;
import com.app.CitaMed.Model.Administrativo.Pago;
import com.app.CitaMed.Model.Agenda.Cita;
import com.app.CitaMed.Repository.Administrativo.PagoRepository;
import com.app.CitaMed.Repository.Agenda.CitaRepository;
import com.app.CitaMed.Service.Administrativo.PagoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PagoServiceTest {

    @Mock
    private PagoRepository pagoRepository;

    @Mock
    private CitaRepository citaRepository;

    @InjectMocks
    private PagoService pagoService;

    private PagoDTO dto;
    private Cita cita;

    @BeforeEach
    void setUp() {
        dto = new PagoDTO();
        dto.setCitaId(1L);
        dto.setMonto(150.0);
        dto.setMetodoPago(MetodoPago.EFECTIVO);
        cita = new Cita();
        cita.setId(1L);
        cita.setEstado(EstadoCita.PROGRAMADA);
    }

    // ─── TEST 1 ───────────────────────────────────────────────────────────────
    @Test
    @DisplayName("No debe registrar pago si la cita no existe")
    void save_debeRetornarError_cuandoCitaNoExiste() {
        when(citaRepository.findById(1L)).thenReturn(Optional.empty());
        String resultado = pagoService.save(dto);
        assertThat(resultado).isEqualTo("Cita no encontrada");
        verify(pagoRepository, never()).save(any());
    }

    // ─── TEST 2 ───────────────────────────────────────────────────────────────
    @Test
    @DisplayName("No debe registrar pago si la cita ya tiene un pago registrado")
    void save_debeRetornarError_cuandoCitaYaTienePago() {
        when(citaRepository.findById(1L)).thenReturn(Optional.of(cita));
        when(pagoRepository.existsByCitaId(1L)).thenReturn(true);
        String resultado = pagoService.save(dto);
        assertThat(resultado).isEqualTo("Esta cita ya tiene un pago registrado");
        verify(pagoRepository, never()).save(any());
    }

    // ─── TEST 3 ───────────────────────────────────────────────────────────────
    @Test
    @DisplayName("No debe registrar pago si la cita está cancelada")
    void save_debeRetornarError_cuandoCitaCancelada() {
        cita.setEstado(EstadoCita.CANCELADA);
        when(citaRepository.findById(1L)).thenReturn(Optional.of(cita));
        when(pagoRepository.existsByCitaId(1L)).thenReturn(false);
        String resultado = pagoService.save(dto);
        assertThat(resultado).isEqualTo("No se puede registrar pago de una cita cancelada");
        verify(pagoRepository, never()).save(any());
    }

    // ─── TEST 4 ───────────────────────────────────────────────────────────────
    @Test
    @DisplayName("Debe registrar el pago correctamente cuando los datos son válidos")
    void save_debeRegistrarPago_cuandoDatosSonValidos() {
        when(citaRepository.findById(1L)).thenReturn(Optional.of(cita));
        when(pagoRepository.existsByCitaId(1L)).thenReturn(false);
        String resultado = pagoService.save(dto);
        assertThat(resultado).isEqualTo("Pago registrado correctamente");
        verify(pagoRepository, times(1)).save(any(Pago.class));
    }

    // ─── TEST 5 ───────────────────────────────────────────────────────────────
    @Test
    @DisplayName("No debe anular un pago si no existe")
    void anular_debeRetornarError_cuandoPagoNoExiste() {
        when(pagoRepository.findById(99L)).thenReturn(Optional.empty());
        String resultado = pagoService.anular(99L);
        assertThat(resultado).isEqualTo("Pago no encontrado");
        verify(pagoRepository, never()).save(any());
    }

    // ─── TEST 6 ───────────────────────────────────────────────────────────────
    @Test
    @DisplayName("Debe anular el pago correctamente cuando existe")
    void anular_debeAnularPago_cuandoExiste() {
        Pago pago = new Pago();
        pago.setId(1L);
        pago.setEstado(EstadoPago.PAGADO);
        when(pagoRepository.findById(1L)).thenReturn(Optional.of(pago));
        String resultado = pagoService.anular(1L);
        assertThat(resultado).isEqualTo("Pago anulado correctamente");
        assertThat(pago.getEstado()).isEqualTo(EstadoPago.ANULADO);
        verify(pagoRepository, times(1)).save(pago);
    }
}
