package com.app.CitaMed.Config;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

class JwtUtilTest {

    private JwtUtil jwtUtil;
    private static final String SECRET = "ThisIsASecretKeyForJwtUtilTestThatIs256BitsLong!!";
    private static final long EXPIRATION = 3600000;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil(SECRET, EXPIRATION);
    }

    @Test
    void generarToken_deberiaCrearTokenValido() {
        String token = jwtUtil.generarToken("testuser", List.of("ADMIN"), 1L);
        assertNotNull(token);
        assertFalse(token.isBlank());
    }

    @Test
    void extraerUsername_deberiaRetornarUsernameCorrecto() {
        String token = jwtUtil.generarToken("jperez", List.of("MEDICO"), 2L);
        assertEquals("jperez", jwtUtil.extraerUsername(token));
    }

    @Test
    void extraerUserId_deberiaRetornarUserIdCorrecto() {
        String token = jwtUtil.generarToken("testuser", List.of("ADMIN"), 10L);
        assertEquals(10L, jwtUtil.extraerUserId(token));
    }

    @Test
    void extraerRoles_deberiaRetornarRolesCorrectos() {
        String token = jwtUtil.generarToken("testuser", List.of("ADMIN", "MEDICO"), 1L);
        List<String> roles = jwtUtil.extraerRoles(token);
        assertTrue(roles.contains("ADMIN"));
        assertTrue(roles.contains("MEDICO"));
    }

    @Test
    void validarToken_deberiaRetornarTrue_ParaTokenValido() {
        String token = jwtUtil.generarToken("testuser", List.of("ADMIN"), 1L);
        assertTrue(jwtUtil.validarToken(token));
    }

    @Test
    void validarToken_deberiaRetornarFalse_ParaTokenInvalido() {
        assertFalse(jwtUtil.validarToken("token.invalido.aqui"));
    }

    @Test
    void validarToken_deberiaRetornarFalse_ParaTokenVacio() {
        assertFalse(jwtUtil.validarToken(""));
    }

    @Test
    void extraerRoles_deberiaRetornarListaVacia_SiNoHayRoles() {
        String token = jwtUtil.generarToken("testuser", List.of(), 1L);
        assertTrue(jwtUtil.extraerRoles(token).isEmpty());
    }
}
