package com.app.CitaMed.Service.Medico;
import com.app.CitaMed.DTO.MedicoDTO;
import com.app.CitaMed.Enums.Rol;
import com.app.CitaMed.Model.Medico.Especialidad;
import com.app.CitaMed.Model.Medico.Medico;
import com.app.CitaMed.Model.Administrativo.Usuario;
import com.app.CitaMed.Repository.Medico.EspecialidadRepository;
import com.app.CitaMed.Repository.Medico.MedicoRepository;
import com.app.CitaMed.Repository.Administrativo.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor

public class MedicoService {
    private final MedicoRepository medicoRepository;
    private final EspecialidadRepository especialidadRepository;
    private final UsuarioRepository usuarioRepository;

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
        if (medicoRepository.existsByDni(dto.getDni()))
            return "Ya existe un médico con ese DNI";

        if (usuarioRepository.existsByUserName(dto.getUserName()))
            return "El nombre de usuario ya está en uso";

        Especialidad especialidad = especialidadRepository.findById(dto.getEspecialidadId()).orElse(null);
        if (especialidad == null) return "Especialidad no encontrada";

        Usuario usuario = new Usuario();
        usuario.setUserName(dto.getUserName());
        usuario.setPassword(dto.getPassword());
        usuario.setRol(Rol.MEDICO);
        usuario.setActivo(true);
        usuarioRepository.save(usuario);

        Medico medico = new Medico();
        medico.setNombre(dto.getNombre().toUpperCase());
        medico.setApellidoPaterno(dto.getApellidoPaterno().toUpperCase());
        medico.setApellidoMaterno(dto.getApellidoMaterno().toUpperCase());
        medico.setDni(dto.getDni());
        medico.setTelefono(dto.getTelefono());
        medico.setDireccion(dto.getDireccion());
        medico.setEmail(dto.getEmail());
        medico.setFechaNacimiento(dto.getFechaNacimiento());
        medico.setGenero(dto.getGenero());
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
