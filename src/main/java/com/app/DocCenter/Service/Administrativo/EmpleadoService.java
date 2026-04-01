package com.app.DocCenter.Service.Administrativo;
import com.app.DocCenter.Model.Administrativo.Empleado;
import com.app.DocCenter.Model.Personas.Persona;
import com.app.DocCenter.Model.Personas.Usuario;
import com.app.DocCenter.Repository.Administrativo.EmpleadoRepository;
import com.app.DocCenter.Repository.Personas.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class EmpleadoService {
    private final EmpleadoRepository empleadoRepository;
    private final UsuarioRepository usuarioRepository;
    // private final PasswordEncoder passwordEncoder;

    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    public Empleado findById(Long id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    public List<Empleado> findActivos() {
        return empleadoRepository.findByActivoTrue();
    }

    public String save(EmpleadoDTO dto) {
        if (empleadoRepository.existsByPersonaDni(dto.getDni()))
            return "Ya existe un empleado con ese DNI";

        if (usuarioRepository.existsByUserName(dto.getUserName()))
            return "El nombre de usuario ya está en uso";

        Usuario usuario = new Usuario();
        usuario.setUserName(dto.getUserName());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario.setRol(dto.getRol());
        usuario.setActivo(true);
        usuarioRepository.save(usuario);

        Persona persona = new Persona();
        persona.setNombre(dto.getNombre().toUpperCase());
        persona.setApellidoPaterno(dto.getApellidoPaterno().toUpperCase());
        persona.setApellidoMaterno(dto.getApellidoMaterno().toUpperCase());
        persona.setDni(dto.getDni());
        persona.setTelefono(dto.getTelefono());
        persona.setDireccion(dto.getDireccion());
        persona.setEmail(dto.getEmail());
        persona.setFechaNacimiento(dto.getFechaNacimiento());
        persona.setGenero(dto.getGenero());

        Empleado empleado = new Empleado();
        empleado.setPersona(persona);
        empleado.setCargo(dto.getCargo());
        empleado.setSalario(dto.getSalario());
        empleado.setFechaIngreso(dto.getFechaIngreso());
        empleado.setUsuario(usuario);
        empleado.setActivo(true);
        empleadoRepository.save(empleado);
        return "Empleado registrado correctamente";
    }

    public String update(Long id, EmpleadoDTO dto) {
        Empleado empleado = empleadoRepository.findById(id).orElse(null);
        if (empleado == null) return "Empleado no encontrado";

        Persona persona = empleado.getPersona();
        persona.setNombre(dto.getNombre().toUpperCase());
        persona.setApellidoPaterno(dto.getApellidoPaterno().toUpperCase());
        persona.setApellidoMaterno(dto.getApellidoMaterno().toUpperCase());
        persona.setTelefono(dto.getTelefono());
        persona.setDireccion(dto.getDireccion());
        persona.setEmail(dto.getEmail());
        empleado.setPersona(persona);
        empleado.setCargo(dto.getCargo());
        empleado.setSalario(dto.getSalario());
        empleadoRepository.save(empleado);
        return "Empleado actualizado correctamente";
    }

    public String toggleActivo(Long id) {
        Empleado empleado = empleadoRepository.findById(id).orElse(null);
        if (empleado == null) return "Empleado no encontrado";
        empleado.setActivo(!empleado.isActivo());
        empleadoRepository.save(empleado);
        return empleado.isActivo() ? "Empleado activado" : "Empleado desactivado";
    }
}
