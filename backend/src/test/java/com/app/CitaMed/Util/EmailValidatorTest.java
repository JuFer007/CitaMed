package com.app.CitaMed.Util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class EmailValidatorTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  "})
    void validar_deberiaLanzarExcepcion_CuandoEmailEsNuloOVacio(String email) {
        assertThrows(IllegalArgumentException.class, () -> EmailValidator.validar(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "correo-sin-arroba",
        "@sin-usuario.com",
        "usuario@incompleto",
        "usuario@.com",
        "usuario@dominio.",
        "usuario@dominio.c"
    })
    void validar_deberiaLanzarExcepcion_CuandoEmailNoTieneFormatoValido(String email) {
        assertThrows(IllegalArgumentException.class, () -> EmailValidator.validar(email));
    }

    @Test
    void validar_deberiaLanzarExcepcion_CuandoEmailExcede100Caracteres() {
        String emailLargo = "a".repeat(93) + "@test.com";
        assertThrows(IllegalArgumentException.class, () -> EmailValidator.validar(emailLargo));
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "usuario@test.com",
        "user.name@domain.co",
        "user_name@sub.domain.com",
        "user+tag@domain.org",
        "a@b.co"
    })
    void validar_noDeberiaLanzarExcepcion_CuandoEmailEsValido(String email) {
        assertDoesNotThrow(() -> EmailValidator.validar(email));
    }

    @Test
    void validar_deberiaTenerMensajeCorrecto_CuandoEmailEsNulo() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> EmailValidator.validar(null));
        assertEquals("El email es obligatorio", ex.getMessage());
    }

    @Test
    void validar_deberiaTenerMensajeCorrecto_CuandoEmailNoTieneFormato() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> EmailValidator.validar("invalido"));
        assertTrue(ex.getMessage().contains("formato válido"));
    }
}
