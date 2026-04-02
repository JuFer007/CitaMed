package com.app.DocCenter.Service.Medico;
import com.app.DocCenter.DTO.DiagnosticoDTO;
import com.app.DocCenter.Model.Medico.Diagnostico;
import com.app.DocCenter.Model.Paciente.ConsultaMedica;
import com.app.DocCenter.Repository.Medico.DiagnosticoRepository;
import com.app.DocCenter.Repository.Paciente.ConsultaMedicaRepository;
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
        diagnostico.setCodigoCIE10(dto.getCodigoCIE10().toUpperCase());
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
}
