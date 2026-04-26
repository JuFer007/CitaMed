package com.app.CitaMed.Controller.Administrativo;
import com.app.CitaMed.Model.Administrativo.Area;
import com.app.CitaMed.Service.Administrativo.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/area")
@RequiredArgsConstructor

public class AreaController {
    private final AreaService areaService;

    @GetMapping
    public ResponseEntity<List<Area>> findAll() {
        return ResponseEntity.ok(areaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Area> findById(@PathVariable Long id) {
        Area area = areaService.findById(id);
        if (area == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(area);
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody Area area) {
        String resultado = areaService.save(area);
        if (!resultado.equals("Área registrada correctamente"))
            return ResponseEntity.badRequest().body(resultado);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Area area) {
        String resultado = areaService.update(id, area);
        if (resultado.equals("Área no encontrada"))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(resultado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        String resultado = areaService.delete(id);
        if (resultado.equals("Área no encontrada"))
            return ResponseEntity.notFound().build();
        if (resultado.contains("No se puede eliminar"))
            return ResponseEntity.badRequest().body(resultado);
        return ResponseEntity.ok(resultado);
    }
}
