package com.app.CitaMed.Util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class DniValidatorTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  "})
    void validar_deberiaLanzarExcepcion_CuandoDniEsNuloOVacio(String dni) {
        assertThrows(IllegalArgumentException.class, () -> DniValidator.validar(dni));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1234567", "123456789", "abcdefgh", "1234 678", "1234567a"})
    void validar_deberiaLanzarExcepcion_CuandoDniNoTiene8Digitos(String dni) {
        assertThrows(IllegalArgumentException.class, () -> DniValidator.validar(dni));
    }

    @ParameterizedTest
    @ValueSource(strings = {"12345678", "00000000", "87654321"})
    void validar_noDeberiaLanzarExcepcion_CuandoDniEsValido(String dni) {
        assertDoesNotThrow(() -> DniValidator.validar(dni));
    }

    @Test
    void validar_deberiaTenerMensajeCorrecto_CuandoDniEsNulo() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> DniValidator.validar(null));
        assertEquals("El DNI es obligatorio", ex.getMessage());
    }

    @Test
    void validar_deberiaTenerMensajeCorrecto_CuandoDniNoTiene8Digitos() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> DniValidator.validar("12345"));
        assertEquals("El DNI debe tener 8 dígitos numéricos", ex.getMessage());
    }
}
