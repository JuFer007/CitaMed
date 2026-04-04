package com.app.CitaMed.Service.Medico;
import com.app.CitaMed.DTO.DiagnosticoDTO;
import com.app.CitaMed.Model.Medico.Diagnostico;
import com.app.CitaMed.Model.Paciente.ConsultaMedica;
import com.app.CitaMed.Repository.Medico.DiagnosticoRepository;
import com.app.CitaMed.Repository.Paciente.ConsultaMedicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor

public class DiagnosticoService {
    private final DiagnosticoRepository diagnosticoRepository;
    private final ConsultaMedicaRepository consultaMedicaRepository;

    public List<Diagnostico> findByConsulta(Long consultaId) {
        return diagnosticoRepository.findByConsultaId(consultaId);
    }

    public String save(DiagnosticoDTO dto) {
        ConsultaMedica consulta = consultaMedicaRepository.findById(dto.getConsultaId()).orElse(null);
        if (consulta == null) return "Consulta médica no encontrada";

        Diagnostico diagnostico = new Diagnostico();
        diagnostico.setConsulta(consulta);
        diagnostico.setEnfermedad(dto.getEnfermedad().toUpperCase());
        diagnostico.setDescripcion(dto.getDescripcion());
        diagnosticoRepository.save(diagnostico);
        return "Diagnóstico registrado correctamente";
    }

    public String delete(Long id) {
        if (!diagnosticoRepository.existsById(id))
            return "Diagnóstico no encontrado";
        diagnosticoRepository.deleteById(id);
        return "Diagnóstico eliminado correctamente";
    }

    public List<Diagnostico> findAll() {
        return diagnosticoRepository.findAll();
    }
}
