package com.app.DocCenter.Service.Paciente;
import com.app.DocCenter.DTO.PacienteDTO;
import com.app.DocCenter.Model.Paciente.HistorialMedico;
import com.app.DocCenter.Model.Paciente.Paciente;
import com.app.DocCenter.Model.Personas.Persona;
import com.app.DocCenter.Repository.Paciente.HistorialMedicoRepository;
import com.app.DocCenter.Repository.Paciente.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor

public class PacienteService {
    private final PacienteRepository pacienteRepository;
    private final HistorialMedicoRepository historialMedicoRepository;

    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    public Paciente findById(Long id) {
        return pacienteRepository.findById(id).orElse(null);
    }

    public Paciente findByDni(String dni) {
        return pacienteRepository.findByPersonaDni(dni);
    }

    public String save(PacienteDTO dto) {
        if (pacienteRepository.existsByPersonaDni(dto.getDni()))
            return "Ya existe un paciente con ese DNI";

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

        Paciente paciente = new Paciente();
        paciente.setPersona(persona);
        paciente.setGrupoSanguineo(dto.getGrupoSanguineo());
        pacienteRepository.save(paciente);

        HistorialMedico historial = new HistorialMedico();
        historial.setPaciente(paciente);
        historialMedicoRepository.save(historial);

        return "Paciente registrado correctamente";
    }

    public String update(Long id, PacienteDTO dto) {
        Paciente paciente = pacienteRepository.findById(id).orElse(null);
        if (paciente == null) return "Paciente no encontrado";
        Persona persona = paciente.getPersona();
        persona.setNombre(dto.getNombre().toUpperCase());
        persona.setApellidoPaterno(dto.getApellidoPaterno().toUpperCase());
        persona.setApellidoMaterno(dto.getApellidoMaterno().toUpperCase());
        persona.setTelefono(dto.getTelefono());
        persona.setDireccion(dto.getDireccion());
        persona.setEmail(dto.getEmail());
        paciente.setPersona(persona);
        pacienteRepository.save(paciente);
        return "Paciente actualizado correctamente";
    }
}
