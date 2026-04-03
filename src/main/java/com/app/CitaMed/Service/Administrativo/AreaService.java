package com.app.DocCenter.Service.Administrativo;
import com.app.DocCenter.Model.Administrativo.Area;
import com.app.DocCenter.Repository.Administrativo.AreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor

public class AreaService {
    private final AreaRepository areaRepository;

    public List<Area> findAll() {
        return areaRepository.findAll();
    }

    public Area findById(Long id) {
        return areaRepository.findById(id).orElse(null);
    }

    public String save(Area area) {
        if (areaRepository.existsByNombre(area.getNombre().toUpperCase()))
            return "Ya existe un área con ese nombre";
        area.setNombre(area.getNombre().toUpperCase());
        areaRepository.save(area);
        return "Área registrada correctamente";
    }

    public String update(Long id, Area dto) {
        Area area = areaRepository.findById(id).orElse(null);
        if (area == null) return "Área no encontrada";
        area.setNombre(dto.getNombre().toUpperCase());
        area.setDescripcion(dto.getDescripcion());
        areaRepository.save(area);
        return "Área actualizada correctamente";
    }

    public String delete(Long id) {
        Area area = areaRepository.findById(id).orElse(null);
        if (area == null) return "Área no encontrada";
        if (!area.getConsultorios().isEmpty())
            return "No se puede eliminar un área que tiene consultorios asignados";
        areaRepository.deleteById(id);
        return "Área eliminada correctamente";
    }
}
