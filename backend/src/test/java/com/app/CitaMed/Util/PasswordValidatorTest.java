package com.app.CitaMed.Util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PasswordValidatorTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  "})
    void validar_deberiaLanzarExcepcion_CuandoPasswordEsNuloOVacio(String password) {
        assertThrows(IllegalArgumentException.class, () -> PasswordValidator.validar(password));
    }

    @Test
    void validar_deberiaLanzarExcepcion_CuandoPasswordEsMenorA6Caracteres() {
        assertThrows(IllegalArgumentException.class, () -> PasswordValidator.validar("abc"));
    }

    @Test
    void validar_deberiaLanzarExcepcion_CuandoPasswordEsMayorA60Caracteres() {
        String longPassword = "a".repeat(61);
        assertThrows(IllegalArgumentException.class, () -> PasswordValidator.validar(longPassword));
    }

    @Test
    void validar_noDeberiaLanzarExcepcion_CuandoPasswordEsValido() {
        assertDoesNotThrow(() -> PasswordValidator.validar("password123"));
        assertDoesNotThrow(() -> PasswordValidator.validar("a".repeat(6)));
        assertDoesNotThrow(() -> PasswordValidator.validar("a".repeat(60)));
    }

    @Test
    void validar_deberiaTenerMensajeCorrecto_CuandoPasswordEsNulo() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> PasswordValidator.validar(null));
        assertEquals("La contraseña es obligatoria", ex.getMessage());
    }

    @Test
    void validar_deberiaTenerMensajeCorrecto_CuandoPasswordEsCorta() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> PasswordValidator.validar("abc"));
        assertEquals("La contraseña debe tener al menos 6 caracteres", ex.getMessage());
    }
}
