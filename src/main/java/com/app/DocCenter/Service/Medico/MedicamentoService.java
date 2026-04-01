package com.app.DocCenter.Service.Medico;
import com.app.DocCenter.Model.Medico.Medicamento;
import com.app.DocCenter.Model.Medico.Tratamiento;
import com.app.DocCenter.Repository.Medico.MedicamentoRepository;
import com.app.DocCenter.Repository.Medico.TratamientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class MedicamentoService {
    private final MedicamentoRepository medicamentoRepository;
    private final TratamientoRepository tratamientoRepository;

    public List<Medicamento> findByTratamiento(Long tratamientoId) {
        return medicamentoRepository.findByTratamientoId(tratamientoId);
    }

    public String save(MedicamentoDTO dto) {
        Tratamiento tratamiento = tratamientoRepository.findById(dto.getTratamientoId()).orElse(null);
        if (tratamiento == null) return "Tratamiento no encontrado";

        Medicamento medicamento = new Medicamento();
        medicamento.setTratamiento(tratamiento);
        medicamento.setNombre(dto.getNombre().toUpperCase());
        medicamento.setDosis(dto.getDosis());
        medicamento.setFrecuencia(dto.getFrecuencia());
        medicamento.setDuracionDias(dto.getDuracionDias());
        medicamento.setInstrucciones(dto.getInstrucciones());
        medicamentoRepository.save(medicamento);
        return "Medicamento registrado correctamente";
    }

    public String delete(Long id) {
        if (!medicamentoRepository.existsById(id))
            return "Medicamento no encontrado";
        medicamentoRepository.deleteById(id);
        return "Medicamento eliminado correctamente";
    }
}
