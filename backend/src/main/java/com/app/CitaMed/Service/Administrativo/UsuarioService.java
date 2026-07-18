package com.app.CitaMed.Service.Administrativo;
import com.app.CitaMed.DTO.UsuarioUpdateDTO;
import com.app.CitaMed.Enums.Rol;
import com.app.CitaMed.Model.Administrativo.Usuario;
import com.app.CitaMed.Repository.Administrativo.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public List<Usuario> findAllUsers() {
        return usuarioRepository.findAll();
    }

    @Transactional
    public String saveUser(Usuario usuario) {
        if (usuarioRepository.existsByUserName(usuario.getUserName())) {
            return "El usuario ya está registrado. Intente de nuevo";
        }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
        return "Usuario guardado correctamente";
    }

    public Optional<Usuario> findByUsuario(String userName) {
        return usuarioRepository.findByUserName(userName);
    }

    @Transactional
    public String updateStatus(Long id) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setActivo(!usuario.isActivo());
            usuarioRepository.save(usuario);
            return usuario.isActivo() ? "Usuario activado correctamente" : "Usuario desactivado correctamente";
        }).orElse("Usuario no encontrado");
    }

    @Transactional
    public String changeRol(Long id, Rol nuevoRol) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) return "Usuario no encontrado";

        usuario.setRol(nuevoRol);
        usuarioRepository.save(usuario);
        return "Rol actualizado a " + nuevoRol;
    }

    public boolean verificarContrasena(String passwordPlana, String passwordHash) {
        try {
            return passwordEncoder.matches(passwordPlana, passwordHash);
        } catch (Exception e) {
            return passwordPlana.equals(passwordHash);
        }
    }

    @Transactional
    public String generarCodigoRecuperacion(Usuario usuario) {
        String codigo;
        do {
            codigo = String.valueOf(100000 + (int) (Math.random() * 900000));
        } while (usuarioRepository.findByResetToken(codigo).isPresent());

        usuario.setResetToken(codigo);
        usuario.setResetTokenExpiry(LocalDateTime.now().plusMinutes(15));
        usuarioRepository.save(usuario);
        return codigo;
    }

    @Transactional
    public void restablecerContrasena(String token, String nuevaPassword) {
        Usuario usuario = usuarioRepository.findByResetToken(token)
                .orElseThrow(() -> new RuntimeException("Código inválido"));

        if (usuario.getResetTokenExpiry() == null || usuario.getResetTokenExpiry().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("El código ha expirado. Solicita uno nuevo.");
        }

        usuario.setPassword(passwordEncoder.encode(nuevaPassword));
        usuario.setResetToken(null);
        usuario.setResetTokenExpiry(null);
        usuarioRepository.save(usuario);
    }

    @Transactional
    public String updateUser(Long id, UsuarioUpdateDTO dto) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) return "Usuario no encontrado";

        if (dto.getUserName() != null && !dto.getUserName().isBlank()) {
            String newName = dto.getUserName().trim();
            if (!newName.equals(usuario.getUserName()) && usuarioRepository.existsByUserName(newName)) {
                return "El nombre de usuario ya está en uso";
            }
            usuario.setUserName(newName);
        }

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        if (dto.getRol() != null) {
            usuario.setRol(dto.getRol());
        }

        usuarioRepository.save(usuario);
        return "Usuario actualizado correctamente";
    }

    @Transactional
    public String deleteUser(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) return "Usuario no encontrado";

        if (!usuario.isActivo()) return "El usuario ya está inactivo";

        usuario.setActivo(false);
        usuarioRepository.save(usuario);
        return "Usuario eliminado correctamente";
    }
}