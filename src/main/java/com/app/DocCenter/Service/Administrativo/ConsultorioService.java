package com.app.DocCenter.Service.Administrativo;
import com.app.DocCenter.DTO.ConsultorioDTO;
import com.app.DocCenter.Model.Administrativo.Area;
import com.app.DocCenter.Model.Administrativo.Consultorio;
import com.app.DocCenter.Repository.Administrativo.AreaRepository;
import com.app.DocCenter.Repository.Administrativo.ConsultorioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ConsultorioService {
    private final ConsultorioRepository consultorioRepository;
    private final AreaRepository areaRepository;

    public List<Consultorio> findAll() {
        return consultorioRepository.findAll();
    }

    public List<Consultorio> findDisponibles() {
        return consultorioRepository.findByDisponibleTrue();
    }

    public List<Consultorio> findByArea(Long areaId) {
        return consultorioRepository.findByAreaId(areaId);
    }

    public Consultorio findById(Long id) {
        return consultorioRepository.findById(id).orElse(null);
    }

    public String save(ConsultorioDTO dto) {
        Area area = areaRepository.findById(dto.getAreaId()).orElse(null);
        if (area == null) return "Área no encontrada";

        if (consultorioRepository.existsByNumero(dto.getNumero()))
            return "Ya existe un consultorio con ese número";

        Consultorio consultorio = new Consultorio();
        consultorio.setNumero(dto.getNumero());
        consultorio.setDescripcion(dto.getDescripcion());
        consultorio.setArea(area);
        consultorio.setDisponible(true);
        consultorioRepository.save(consultorio);
        return "Consultorio registrado correctamente";
    }

    public String update(Long id, ConsultorioDTO dto) {
        Consultorio consultorio = consultorioRepository.findById(id).orElse(null);
        if (consultorio == null) return "Consultorio no encontrado";

        Area area = areaRepository.findById(dto.getAreaId()).orElse(null);
        if (area == null) return "Área no encontrada";

        consultorio.setNumero(dto.getNumero());
        consultorio.setDescripcion(dto.getDescripcion());
        consultorio.setArea(area);
        consultorioRepository.save(consultorio);
        return "Consultorio actualizado correctamente";
    }

    public String toggleDisponible(Long id) {
        Consultorio consultorio = consultorioRepository.findById(id).orElse(null);
        if (consultorio == null) return "Consultorio no encontrado";
        consultorio.setDisponible(!consultorio.isDisponible());
        consultorioRepository.save(consultorio);
        return consultorio.isDisponible() ? "Consultorio disponible" : "Consultorio no disponible";
    }
}
