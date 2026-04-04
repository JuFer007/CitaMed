package com.app.CitaMed.Controller.Medico;
import com.app.CitaMed.DTO.TratamientoDTO;
import com.app.CitaMed.Model.Medico.Especialidad;
import com.app.CitaMed.Model.Medico.Tratamiento;
import com.app.CitaMed.Service.Medico.TratamientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/tratamiento")
@RequiredArgsConstructor

public class TratamientoController {
    private final TratamientoService tratamientoService;

    @GetMapping("/consulta/{consultaId}")
    public ResponseEntity<Tratamiento> findByConsulta(@PathVariable Long consultaId) {
        Tratamiento t = tratamientoService.findByConsulta(consultaId);
        if (t == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(t);
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody TratamientoDTO dto) {
        String resultado = tratamientoService.save(dto);
        if (!resultado.equals("Tratamiento registrado correctamente"))
            return ResponseEntity.badRequest().body(resultado);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody TratamientoDTO dto) {
        String resultado = tratamientoService.update(id, dto);
        if (resultado.equals("Tratamiento no encontrado"))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(resultado);
    }

    @GetMapping
    public ResponseEntity<List<Tratamiento>> findAll() {
        return ResponseEntity.ok(tratamientoService.findAll());
    }
}
