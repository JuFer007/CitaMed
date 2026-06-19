package com.app.CitaMed.Util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class UserNameValidatorTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  "})
    void validar_deberiaLanzarExcepcion_CuandoUserNameEsNuloOVacio(String userName) {
        assertThrows(IllegalArgumentException.class, () -> UserNameValidator.validar(userName));
    }

    @Test
    void validar_deberiaLanzarExcepcion_CuandoUserNameEsMenorA4Caracteres() {
        assertThrows(IllegalArgumentException.class, () -> UserNameValidator.validar("abc"));
    }

    @Test
    void validar_deberiaLanzarExcepcion_CuandoUserNameEsMayorA20Caracteres() {
        assertThrows(IllegalArgumentException.class, () -> UserNameValidator.validar("a".repeat(21)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"user name", "user@name", "user#name", "usuario!", "usuarioñ"})
    void validar_deberiaLanzarExcepcion_CuandoUserNameTieneCaracteresInvalidos(String userName) {
        assertThrows(IllegalArgumentException.class, () -> UserNameValidator.validar(userName));
    }

    @ParameterizedTest
    @ValueSource(strings = {"user123", "user.name", "user_name", "admin", "usuario.test_1"})
    void validar_noDeberiaLanzarExcepcion_CuandoUserNameEsValido(String userName) {
        assertDoesNotThrow(() -> UserNameValidator.validar(userName));
    }

    @Test
    void validar_deberiaTenerMensajeCorrecto_CuandoUserNameEsNulo() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> UserNameValidator.validar(null));
        assertEquals("El nombre de usuario es obligatorio", ex.getMessage());
    }

    @Test
    void validar_deberiaTenerMensajeCorrecto_CuandoUserNameTieneCaracteresInvalidos() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> UserNameValidator.validar("user name"));
        assertTrue(ex.getMessage().contains("solo puede contener"));
    }
}
