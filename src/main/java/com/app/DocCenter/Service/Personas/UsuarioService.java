package com.app.DocCenter.Service.Personas;
import com.app.DocCenter.Enums.Rol;
import com.app.DocCenter.Model.Personas.Usuario;
import com.app.DocCenter.Repository.Personas.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor

public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public List<Usuario> findAllUsers() {
        return usuarioRepository.findAll();
    }

    public String saveUser(Usuario usuario) {
        boolean existe = usuarioRepository.existsByUserName(usuario.getUserName());
        if (existe) {
            return  "El usuario ya esta registrado. Intente de nuevo";
        }
        usuarioRepository.save(usuario);
        return "Usuario guardado correctamente";
    }

    public Usuario findByUserName(String userName) {
        return usuarioRepository.findByUserName(userName);
    }

    public String updateStatus(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);

        if(usuario == null){
            return "Usuario no encontrado";
        }
        usuario.setActivo(!usuario.isActivo());
        usuarioRepository.save(usuario);
        if(usuario.isActivo()){
            return "Usuario activado correctamente";
        }else{
            return "Usuario desactivado correctamente";
        }
    }

    public String changeRol(Long id, Rol nuevoRol) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if(usuario == null){
            return "Usuario no encontrado";
        }
        usuario.setRol(nuevoRol);
        usuarioRepository.save(usuario);
        return "Rol actualizado a " + nuevoRol;
    }
}
