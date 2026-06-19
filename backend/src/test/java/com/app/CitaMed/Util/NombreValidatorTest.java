package com.app.CitaMed.Util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class NombreValidatorTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  "})
    void validar_deberiaLanzarExcepcion_CuandoNombreEsNuloOVacio(String nombre) {
        assertThrows(IllegalArgumentException.class, () -> NombreValidator.validar(nombre, "nombre"));
    }

    @Test
    void validar_deberiaLanzarExcepcion_CuandoNombreExcede80Caracteres() {
        String nombreLargo = "a".repeat(81);
        assertThrows(IllegalArgumentException.class, () -> NombreValidator.validar(nombreLargo, "nombre"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Juan123", "Carlos@", "Nombre#", "Test!"})
    void validar_deberiaLanzarExcepcion_CuandoNombreTieneCaracteresInvalidos(String nombre) {
        assertThrows(IllegalArgumentException.class, () -> NombreValidator.validar(nombre, "nombre"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Juan", "Carlos Alberto", "María José", "José"})
    void validar_noDeberiaLanzarExcepcion_CuandoNombreEsValido(String nombre) {
        assertDoesNotThrow(() -> NombreValidator.validar(nombre, "nombre"));
    }

    @Test
    void validar_noDeberiaLanzarExcepcion_CuandoNombreTieneExactamente80Caracteres() {
        String nombre80 = "a".repeat(80);
        assertDoesNotThrow(() -> NombreValidator.validar(nombre80, "nombre"));
    }

    @Test
    void validar_deberiaUsarElNombreDelCampo_EnElMensajeDeError() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> NombreValidator.validar(null, "apellido paterno"));
        assertEquals("El apellido paterno es obligatorio", ex.getMessage());
    }

    @Test
    void validar_deberiaTenerMensajeCorrecto_ParaCaracteresInvalidos() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> NombreValidator.validar("Juan123", "nombre"));
        assertTrue(ex.getMessage().contains("solo puede contener letras"));
    }
}
