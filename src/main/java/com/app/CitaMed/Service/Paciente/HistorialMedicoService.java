package com.app.CitaMed.Service.Paciente;
import com.app.CitaMed.Model.Paciente.HistorialMedico;
import com.app.CitaMed.Repository.Paciente.HistorialMedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class HistorialMedicoService {
    private final HistorialMedicoRepository historialMedicoRepository;

    public HistorialMedico findByPaciente(Long pacienteId) {
        return historialMedicoRepository.findByPacienteId(pacienteId).orElse(null);
    }

    public HistorialMedico findById(Long id) {
        return historialMedicoRepository.findById(id).orElse(null);
    }
}
