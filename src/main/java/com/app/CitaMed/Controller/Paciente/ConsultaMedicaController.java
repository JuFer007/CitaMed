package com.app.CitaMed.Controller.Paciente;
import com.app.CitaMed.DTO.ConsultaMedicaDTO;
import com.app.CitaMed.Model.Paciente.ConsultaMedica;
import com.app.CitaMed.Service.Paciente.ConsultaMedicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/consultaMedica")
@RequiredArgsConstructor

public class ConsultaMedicaController {
    private final ConsultaMedicaService consultaMedicaService;

    @GetMapping
    public ResponseEntity<List<ConsultaMedica>> findAll() {
        return ResponseEntity.ok(consultaMedicaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaMedica> findById(@PathVariable Long id) {
        ConsultaMedica c = consultaMedicaService.findById(id);
        if (c == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(c);
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<ConsultaMedica>> findByPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(consultaMedicaService.findByPaciente(pacienteId));
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody ConsultaMedicaDTO dto) {
        String resultado = consultaMedicaService.save(dto);
        if (!resultado.equals("Consulta registrada correctamente"))
            return ResponseEntity.badRequest().body(resultado);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }
}
