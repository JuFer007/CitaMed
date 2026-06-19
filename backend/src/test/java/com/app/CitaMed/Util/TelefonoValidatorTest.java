package com.app.CitaMed.Util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class TelefonoValidatorTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  "})
    void validar_deberiaLanzarExcepcion_CuandoTelefonoEsNuloOVacio(String telefono) {
        assertThrows(IllegalArgumentException.class, () -> TelefonoValidator.validar(telefono));
    }

    @ParameterizedTest
    @ValueSource(strings = {"123456", "1234567890123456", "abcdefghi", "1234 6789"})
    void validar_deberiaLanzarExcepcion_CuandoTelefonoNoTieneFormatoValido(String telefono) {
        assertThrows(IllegalArgumentException.class, () -> TelefonoValidator.validar(telefono));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1234567", "987654321", "123456789012345"})
    void validar_noDeberiaLanzarExcepcion_CuandoTelefonoEsValido(String telefono) {
        assertDoesNotThrow(() -> TelefonoValidator.validar(telefono));
    }

    @Test
    void validar_deberiaTenerMensajeCorrecto_CuandoTelefonoEsNulo() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> TelefonoValidator.validar(null));
        assertEquals("El teléfono es obligatorio", ex.getMessage());
    }
}
