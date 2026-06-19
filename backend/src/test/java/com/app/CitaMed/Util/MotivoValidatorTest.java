package com.app.CitaMed.Util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class MotivoValidatorTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  "})
    void validar_deberiaLanzarExcepcion_CuandoMotivoEsNuloOVacio(String motivo) {
        assertThrows(IllegalArgumentException.class, () -> MotivoValidator.validar(motivo));
    }

    @Test
    void validar_deberiaLanzarExcepcion_CuandoMotivoExcede300Caracteres() {
        String motivoLargo = "a".repeat(301);
        assertThrows(IllegalArgumentException.class, () -> MotivoValidator.validar(motivoLargo));
    }

    @Test
    void validar_noDeberiaLanzarExcepcion_CuandoMotivoEsValido() {
        assertDoesNotThrow(() -> MotivoValidator.validar("Consulta general"));
        assertDoesNotThrow(() -> MotivoValidator.validar("a".repeat(300)));
    }

    @Test
    void validar_deberiaTenerMensajeCorrecto_CuandoMotivoEsNulo() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> MotivoValidator.validar(null));
        assertEquals("El motivo de la consulta es obligatorio", ex.getMessage());
    }
}
