package com.app.CitaMed.Controller.Medico;

import com.app.CitaMed.DTO.MedicamentoDTO;
import com.app.CitaMed.Model.Medico.Medicamento;
import com.app.CitaMed.Service.Medico.MedicamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/medicamento")
@RequiredArgsConstructor

public class MedicamentoController {
    private final MedicamentoService medicamentoService;

    @GetMapping("/tratamiento/{tratamientoId}")
    public ResponseEntity<List<Medicamento>> findByTratamiento(@PathVariable Long tratamientoId) {
        return ResponseEntity.ok(medicamentoService.findByTratamiento(tratamientoId));
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody MedicamentoDTO dto) {
        String resultado = medicamentoService.save(dto);
        if (!resultado.equals("Medicamento registrado correctamente"))
            return ResponseEntity.badRequest().body(resultado);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        String resultado = medicamentoService.delete(id);
        if (resultado.equals("Medicamento no encontrado"))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(resultado);
    }

    @GetMapping
    public ResponseEntity<List<Medicamento>> findAll() {
        return ResponseEntity.ok(medicamentoService.findAll());
    }
}
