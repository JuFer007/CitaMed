package com.app.CitaMed.Controller.Paciente;
import com.app.CitaMed.DTO.PacienteDTO;
import com.app.CitaMed.Model.Paciente.Paciente;
import com.app.CitaMed.Service.Paciente.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/paciente")
@RequiredArgsConstructor

public class PacienteController {
    private final PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> findAll() {
        return ResponseEntity.ok(pacienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> findById(@PathVariable Long id) {
        Paciente p = pacienteService.findById(id);
        if (p == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(p);
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<Paciente> findByDni(@PathVariable String dni) {
        Paciente p = pacienteService.findByDni(dni);
        if (p == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(p);
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody PacienteDTO dto) {
        String resultado = pacienteService.save(dto);
        if (!resultado.equals("Paciente registrado correctamente"))
            return ResponseEntity.badRequest().body(resultado);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody PacienteDTO dto) {
        String resultado = pacienteService.update(id, dto);
        if (resultado.equals("Paciente no encontrado"))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(resultado);
    }
}
