package com.app.CitaMed.Controller;

import com.app.CitaMed.Config.JwtUtil;
import com.app.CitaMed.Model.Administrativo.Usuario;
import com.app.CitaMed.Service.Administrativo.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("usuario");
        String password = credentials.get("clave");

        System.out.println("DEBUG: Intento de login - Usuario: " + username);
        System.out.println("DEBUG: Clave recibida: " + password);

        Optional<Usuario> usuarioOpt = usuarioService.findByUsuario(username);

        // 1. Validar existencia
        if (usuarioOpt.isEmpty()) {
            System.out.println("DEBUG: El usuario no existe en la BD");
            return ResponseEntity.status(401).body(Map.of("error", "Usuario no encontrado"));
        }

        Usuario user = usuarioOpt.get();
        boolean passwordCoincide = usuarioService.verificarContrasena(password, user.getPassword());

        System.out.println("DEBUG: Contraseña en BD: " + user.getPassword());
        System.out.println("DEBUG: ¿Coinciden?: " + passwordCoincide);

        // 2. Validar estado (activo es boolean)
        if (!user.isActivo()) {
            return ResponseEntity.status(403).body(Map.of("error", "Usuario inactivo"));
        }

        // 3. Validar contraseña
        if (usuarioService.verificarContrasena(password, user.getPassword())) {
            String token = jwtUtil.generarToken(user.getUserName());

            Map<String, Object> response = new HashMap<>();
            response.put("nombre", user.getUserName());
            response.put("token", token);
            response.put("usuario", user.getUserName());
            response.put("perfil", user.getRol().name());

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(Map.of("error", "Contraseña incorrecta"));
        }
    }
}