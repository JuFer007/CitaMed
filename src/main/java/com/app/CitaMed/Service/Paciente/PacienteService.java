package com.app.CitaMed.Service.Paciente;
import com.app.CitaMed.DTO.PacienteDTO;
import com.app.CitaMed.Model.Paciente.HistorialMedico;
import com.app.CitaMed.Model.Paciente.Paciente;
import com.app.CitaMed.Repository.Paciente.HistorialMedicoRepository;
import com.app.CitaMed.Repository.Paciente.PacienteRepository;
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
        return pacienteRepository.findByDni(dni);
    }

    public String save(PacienteDTO dto) {
        if (pacienteRepository.existsByDni(dto.getDni()))
            return "Ya existe un paciente con ese DNI";

        Paciente paciente = new Paciente();
        paciente.setNombre(dto.getNombre().toUpperCase());
        paciente.setApellidoPaterno(dto.getApellidoPaterno().toUpperCase());
        paciente.setApellidoMaterno(dto.getApellidoMaterno().toUpperCase());
        paciente.setDni(dto.getDni());
        paciente.setTelefono(dto.getTelefono());
        paciente.setDireccion(dto.getDireccion());
        paciente.setEmail(dto.getEmail());
        paciente.setFechaNacimiento(dto.getFechaNacimiento());
        paciente.setGenero(dto.getGenero());
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
        paciente.setNombre(dto.getNombre().toUpperCase());
        paciente.setApellidoPaterno(dto.getApellidoPaterno().toUpperCase());
        paciente.setApellidoMaterno(dto.getApellidoMaterno().toUpperCase());
        paciente.setTelefono(dto.getTelefono());
        paciente.setDireccion(dto.getDireccion());
        paciente.setEmail(dto.getEmail());
        pacienteRepository.save(paciente);
        return "Paciente actualizado correctamente";
    }
}
