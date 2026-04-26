package com.app.CitaMed.Service.Paciente;
import com.app.CitaMed.DTO.ConsultaMedicaDTO;
import com.app.CitaMed.Enums.EstadoCita;
import com.app.CitaMed.Model.Agenda.Cita;
import com.app.CitaMed.Model.Paciente.ConsultaMedica;
import com.app.CitaMed.Model.Paciente.HistorialMedico;
import com.app.CitaMed.Repository.Agenda.CitaRepository;
import com.app.CitaMed.Repository.Paciente.ConsultaMedicaRepository;
import com.app.CitaMed.Repository.Paciente.HistorialMedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ConsultaMedicaService {
    private final ConsultaMedicaRepository consultaMedicaRepository;
    private final CitaRepository citaRepository;
    private final HistorialMedicoRepository historialMedicoRepository;

    public List<ConsultaMedica> findAll() {
        return consultaMedicaRepository.findAll();
    }

    public ConsultaMedica findById(Long id) {
        return consultaMedicaRepository.findById(id).orElse(null);
    }

    public List<ConsultaMedica> findByPaciente(Long pacienteId) {
        return consultaMedicaRepository.findByHistorialPacienteId(pacienteId);
    }

    public String save(ConsultaMedicaDTO dto) {
        Cita cita = citaRepository.findById(dto.getCitaId()).orElse(null);
        if (cita == null) return "Cita no encontrada";

        if (cita.getEstado() == EstadoCita.CANCELADA)
            return "No se puede registrar consulta de una cita cancelada";

        if (consultaMedicaRepository.existsByCitaId(dto.getCitaId()))
            return "Ya existe una consulta registrada para esta cita";

        HistorialMedico historial = historialMedicoRepository
                .findByPacienteId(cita.getPaciente().getId()).orElse(null);
        if (historial == null) return "Historial médico del paciente no encontrado";

        ConsultaMedica consulta = new ConsultaMedica();
        consulta.setCita(cita);
        consulta.setHistorial(historial);
        consulta.setObservaciones(dto.getObservaciones());
        consulta.setPeso(dto.getPeso());
        consulta.setTalla(dto.getTalla());
        consulta.setPresionArterial(dto.getPresionArterial());
        consulta.setTemperatura(dto.getTemperatura());
        consultaMedicaRepository.save(consulta);
        cita.setEstado(EstadoCita.ATENDIDA);
        citaRepository.save(cita);
        return "Consulta registrada correctamente";
    }
}
