package com.app.CitaMed.Controller.Administrativo;
import com.app.CitaMed.DTO.UsuarioUpdateDTO;
import com.app.CitaMed.Enums.Rol;
import com.app.CitaMed.Model.Administrativo.Usuario;
import com.app.CitaMed.Service.Administrativo.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/usuario")
@RequiredArgsConstructor

public class UsuarioController {
    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        return ResponseEntity.ok(usuarioService.findAllUsers());
    }

    @GetMapping("/username/{userName}")
    public ResponseEntity<Usuario> findByUserName(@PathVariable String userName) {
        return usuarioService.findByUsuario(userName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody @Valid Usuario usuario) {
        String resultado = usuarioService.saveUser(usuario);
        if (!resultado.equals("Usuario guardado correctamente"))
            return ResponseEntity.badRequest().body(resultado);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<String> toggleActivo(@PathVariable Long id) {
        String resultado = usuarioService.updateStatus(id);
        if (resultado.equals("Usuario no encontrado"))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(resultado);
    }

    @PatchMapping("/{id}/rol")
    public ResponseEntity<String> changeRol(@PathVariable Long id, @RequestParam Rol nuevoRol) {
        String resultado = usuarioService.changeRol(id, nuevoRol);
        if (resultado.equals("Usuario no encontrado"))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(resultado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody UsuarioUpdateDTO dto) {
        String resultado = usuarioService.updateUser(id, dto);
        if (resultado.equals("Usuario no encontrado"))
            return ResponseEntity.notFound().build();
        if (resultado.equals("El nombre de usuario ya está en uso"))
            return ResponseEntity.badRequest().body(resultado);
        return ResponseEntity.ok(resultado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        String resultado = usuarioService.deleteUser(id);
        if (resultado.equals("Usuario no encontrado"))
            return ResponseEntity.notFound().build();
        if (resultado.equals("El usuario ya está inactivo"))
            return ResponseEntity.badRequest().body(resultado);
        return ResponseEntity.ok(resultado);
    }
}