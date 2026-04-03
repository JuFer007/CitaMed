package com.app.DocCenter.Service.Medico;
import com.app.DocCenter.DTO.TratamientoDTO;
import com.app.DocCenter.Model.Medico.Tratamiento;
import com.app.DocCenter.Model.Paciente.ConsultaMedica;
import com.app.DocCenter.Repository.Medico.TratamientoRepository;
import com.app.DocCenter.Repository.Paciente.ConsultaMedicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class TratamientoService {
    private final TratamientoRepository tratamientoRepository;
    private final ConsultaMedicaRepository consultaMedicaRepository;

    public Tratamiento findByConsulta(Long consultaId) {
        return tratamientoRepository.findByConsultaId(consultaId).orElse(null);
    }

    public String save(TratamientoDTO dto) {
        ConsultaMedica consulta = consultaMedicaRepository.findById(dto.getConsultaId()).orElse(null);
        if (consulta == null) return "Consulta médica no encontrada";

        if (tratamientoRepository.existsByConsultaId(dto.getConsultaId()))
            return "Esta consulta ya tiene un tratamiento registrado";

        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setConsulta(consulta);
        tratamiento.setIndicaciones(dto.getIndicaciones());
        tratamiento.setDuracionDias(dto.getDuracionDias());
        tratamientoRepository.save(tratamiento);
        return "Tratamiento registrado correctamente";
    }

    public String update(Long id, TratamientoDTO dto) {
        Tratamiento tratamiento = tratamientoRepository.findById(id).orElse(null);
        if (tratamiento == null) return "Tratamiento no encontrado";
        tratamiento.setIndicaciones(dto.getIndicaciones());
        tratamiento.setDuracionDias(dto.getDuracionDias());
        tratamientoRepository.save(tratamiento);
        return "Tratamiento actualizado correctamente";
    }
}
