package com.app.CitaMed.Service.Administrativo;

import com.app.CitaMed.Config.JwtUtil;
import com.app.CitaMed.Enums.Rol;
import com.app.CitaMed.Model.Administrativo.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthService authService;

    private Usuario usuarioActivo;
    private static final String USERNAME = "jperez";
    private static final String PASSWORD = "password123";
    private static final String TOKEN = "jwt.token.test";

    @BeforeEach
    void setUp() {
        usuarioActivo = new Usuario(1L, USERNAME, "encodedPassword", Rol.ADMIN, true);
    }

    @Test
    void login_deberiaRetornar401_CuandoUsuarioNoExiste() {
        when(usuarioService.findByUsuario(USERNAME)).thenReturn(Optional.empty());

        ResponseEntity<?> response = authService.login(USERNAME, PASSWORD);

        assertEquals(401, response.getStatusCode().value());
        assertTrue(response.getBody().toString().contains("Usuario no encontrado"));
    }

    @Test
    void login_deberiaRetornar403_CuandoUsuarioEstaInactivo() {
        Usuario usuarioInactivo = new Usuario(1L, USERNAME, "encodedPassword", Rol.ADMIN, false);
        when(usuarioService.findByUsuario(USERNAME)).thenReturn(Optional.of(usuarioInactivo));

        ResponseEntity<?> response = authService.login(USERNAME, PASSWORD);

        assertEquals(403, response.getStatusCode().value());
        assertTrue(response.getBody().toString().contains("Usuario inactivo"));
    }

    @Test
    void login_deberiaRetornar401_CuandoContrasenaEsIncorrecta() {
        when(usuarioService.findByUsuario(USERNAME)).thenReturn(Optional.of(usuarioActivo));
        when(usuarioService.verificarContrasena(PASSWORD, usuarioActivo.getPassword())).thenReturn(false);

        ResponseEntity<?> response = authService.login(USERNAME, PASSWORD);

        assertEquals(401, response.getStatusCode().value());
        assertTrue(response.getBody().toString().contains("Contraseña incorrecta"));
    }

    @Test
    void login_deberiaRetornar200_CuandoCredencialesSonCorrectas() {
        when(usuarioService.findByUsuario(USERNAME)).thenReturn(Optional.of(usuarioActivo));
        when(usuarioService.verificarContrasena(PASSWORD, usuarioActivo.getPassword())).thenReturn(true);
        when(jwtUtil.generarToken(USERNAME, List.of(Rol.ADMIN.name()), 1L)).thenReturn(TOKEN);

        ResponseEntity<?> response = authService.login(USERNAME, PASSWORD);

        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    @SuppressWarnings("unchecked")
    void login_deberiaIncluirCamposCorrectos_EnRespuestaExitosa() {
        when(usuarioService.findByUsuario(USERNAME)).thenReturn(Optional.of(usuarioActivo));
        when(usuarioService.verificarContrasena(PASSWORD, usuarioActivo.getPassword())).thenReturn(true);
        when(jwtUtil.generarToken(USERNAME, List.of(Rol.ADMIN.name()), 1L)).thenReturn(TOKEN);

        ResponseEntity<java.util.Map<String, Object>> response =
                (ResponseEntity<java.util.Map<String, Object>>) authService.login(USERNAME, PASSWORD);

        java.util.Map<String, Object> body = response.getBody();
        assertNotNull(body);
        assertEquals(USERNAME, body.get("usuario"));
        assertEquals(TOKEN, body.get("token"));
        assertEquals(Rol.ADMIN.name(), body.get("perfil"));
    }
}
