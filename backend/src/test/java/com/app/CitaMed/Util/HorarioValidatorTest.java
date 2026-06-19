package com.app.CitaMed.Util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.time.LocalDateTime;

class HorarioValidatorTest {

    @Test
    void validar_deberiaLanzarExcepcion_CuandoEsSabado() {
        LocalDateTime sabado = LocalDateTime.of(2026, 6, 20, 10, 0);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> HorarioValidator.validar(sabado));
        assertEquals("Solo se puede reservar de lunes a viernes", ex.getMessage());
    }

    @Test
    void validar_deberiaLanzarExcepcion_CuandoEsDomingo() {
        LocalDateTime domingo = LocalDateTime.of(2026, 6, 21, 10, 0);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> HorarioValidator.validar(domingo));
        assertEquals("Solo se puede reservar de lunes a viernes", ex.getMessage());
    }

    @Test
    void validar_deberiaLanzarExcepcion_CuandoHoraEsAntesDeLas8AM() {
        LocalDateTime lunes = LocalDateTime.of(2026, 6, 22, 7, 59);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> HorarioValidator.validar(lunes));
        assertEquals("Solo se puede reservar de 8:00 AM a 6:00 PM", ex.getMessage());
    }

    @Test
    void validar_deberiaLanzarExcepcion_CuandoHoraEsDespuesDeLas6PM() {
        LocalDateTime lunes = LocalDateTime.of(2026, 6, 22, 18, 1);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> HorarioValidator.validar(lunes));
        assertEquals("Solo se puede reservar de 8:00 AM a 6:00 PM", ex.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
        "2026-06-22, 08:00",
        "2026-06-22, 12:00",
        "2026-06-22, 17:59",
        "2026-06-23, 10:30",
        "2026-06-26, 15:00"
    })
    void validar_noDeberiaLanzarExcepcion_CuandoHorarioEsValido(String fecha, String hora) {
        LocalDateTime fechaHora = LocalDateTime.parse(fecha + "T" + hora + ":00");
        assertDoesNotThrow(() -> HorarioValidator.validar(fechaHora));
    }
}
