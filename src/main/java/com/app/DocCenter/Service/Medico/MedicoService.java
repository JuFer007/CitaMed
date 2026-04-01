package com.app.DocCenter.Service.Medico;
import com.app.DocCenter.DTO.MedicoDTO;
import com.app.DocCenter.Enums.Rol;
import com.app.DocCenter.Model.Medico.Especialidad;
import com.app.DocCenter.Model.Medico.Medico;
import com.app.DocCenter.Model.Personas.Persona;
import com.app.DocCenter.Model.Personas.Usuario;
import com.app.DocCenter.Repository.Medico.EspecialidadRepository;
import com.app.DocCenter.Repository.Medico.MedicoRepository;
import com.app.DocCenter.Repository.Personas.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class MedicoService {
    private final MedicoRepository medicoRepository;
    private final EspecialidadRepository especialidadRepository;
    private final UsuarioRepository usuarioRepository;
    // private final PasswordEncoder passwordEncoder;

    public List<Medico> findAll() {
        return medicoRepository.findAll();
    }

    public Medico findById(Long id) {
        return medicoRepository.findById(id).orElse(null);
    }

    public List<Medico> findByEspecialidad(Long especialidadId) {
        return medicoRepository.findByEspecialidadIdAndActivoTrue(especialidadId);
    }

    public String save(MedicoDTO dto) {
        if (medicoRepository.existsByPersonaDni(dto.getDni()))
            return "Ya existe un médico con ese DNI";

        if (usuarioRepository.existsByUserName(dto.getUserName()))
            return "El nombre de usuario ya está en uso";

        Especialidad especialidad = especialidadRepository.findById(dto.getEspecialidadId()).orElse(null);
        if (especialidad == null)
            return "Especialidad no encontrada";

        Usuario usuario = new Usuario();
        usuario.setUserName(dto.getUserName());
        //usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario.setRol(Rol.MEDICO);
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

        Medico medico = new Medico();
        medico.setPersona(persona);
        medico.setNumeroColegiatura(dto.getNumeroColegiatura());
        medico.setEspecialidad(especialidad);
        medico.setUsuario(usuario);
        medico.setActivo(true);
        medicoRepository.save(medico);
        return "Médico registrado correctamente";
    }

    public String toggleActivo(Long id) {
        Medico medico = medicoRepository.findById(id).orElse(null);
        if (medico == null) return "Médico no encontrado";
        medico.setActivo(!medico.isActivo());
        medicoRepository.save(medico);
        return medico.isActivo() ? "Médico activado" : "Médico desactivado";
    }

    public String updateEspecialidad(Long medicoId, Long especialidadId) {
        Medico medico = medicoRepository.findById(medicoId).orElse(null);
        if (medico == null) return "Médico no encontrado";
        Especialidad especialidad = especialidadRepository.findById(especialidadId).orElse(null);
        if (especialidad == null) return "Especialidad no encontrada";
        medico.setEspecialidad(especialidad);
        medicoRepository.save(medico);
        return "Especialidad actualizada correctamente";
    }
}
