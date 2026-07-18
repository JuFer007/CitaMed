package com.app.CitaMed.Service.Administrativo;
import com.app.CitaMed.Config.JwtUtil;
import com.app.CitaMed.Model.Administrativo.Usuario;
import com.app.CitaMed.Model.Paciente.Paciente;
import com.app.CitaMed.Repository.Paciente.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class AuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;
    private final PacienteRepository pacienteRepository;

    public ResponseEntity<?> login(String username, String password){

        Optional<Usuario> usuarioOpt = Optional.empty();

        if (username != null) {
            if (username.contains("@")) {
                log.info("Login — buscando por email: {}", username);
                usuarioOpt = pacienteRepository.findByEmail(username)
                        .map(Paciente::getUsuario);
                log.info("Login — resultado por email: {}", usuarioOpt.isPresent());
            }
            if (usuarioOpt.isEmpty()) {
                log.info("Login — buscando por username: {}", username);
                usuarioOpt = usuarioService.findByUsuario(username);
                log.info("Login — resultado por username: {}", usuarioOpt.isPresent());
            }
        }

        if(usuarioOpt.isEmpty()){
            return ResponseEntity.status(401)
                    .body(Map.of("error","Usuario no encontrado"));
        }

        Usuario user = usuarioOpt.get();

        if(!user.isActivo()){
            return ResponseEntity.status(403)
                    .body(Map.of("error","Usuario inactivo"));
        }

        if(!usuarioService.verificarContrasena(password,user.getPassword())){
            return ResponseEntity.status(401)
                    .body(Map.of("error","Contraseña incorrecta"));
        }

        java.util.List<String> roles = java.util.List.of(user.getRol().name());
        String token = jwtUtil.generarToken(user.getUserName(), roles, user.getId());

        Map<String,Object> response = new HashMap<>();
        response.put("nombre", user.getUserName());
        response.put("token", token);
        response.put("usuario", user.getUserName());
        response.put("perfil", user.getRol().name());

        if (user.getRol().name().equals("PACIENTE")) {
            pacienteRepository.findByUsuarioId(user.getId())
                .ifPresent(p -> response.put("pacienteId", p.getId()));
        }

        return ResponseEntity.ok(response);
    }
}