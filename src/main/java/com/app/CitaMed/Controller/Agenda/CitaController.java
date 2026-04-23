package com.app.CitaMed.Controller.Agenda;
import com.app.CitaMed.DTO.CitaDTO;
import com.app.CitaMed.Model.Agenda.Cita;
import com.app.CitaMed.Service.Agenda.CitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/cita")
@RequiredArgsConstructor

public class CitaController {
    private final CitaService citaService;

    @GetMapping
    public ResponseEntity<List<Cita>> findAll() {
        return ResponseEntity.ok(citaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> findById(@PathVariable Long id) {
        Cita cita = citaService.findById(id);
        if (cita == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cita);
    }

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<Cita>> findByMedico(@PathVariable Long medicoId) {
        return ResponseEntity.ok(citaService.findByMedico(medicoId));
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Cita>> findByPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(citaService.findByPaciente(pacienteId));
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody CitaDTO dto) {
        String resultado = citaService.save(dto);
        if (!resultado.equals("Cita registrada correctamente"))
            return ResponseEntity.badRequest().body(resultado);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<String> cancelar(@PathVariable Long id) {
        String resultado = citaService.cancelar(id);
        if (resultado.equals("Cita no encontrada"))
            return ResponseEntity.notFound().build();
        if (resultado.contains("No se puede"))
            return ResponseEntity.badRequest().body(resultado);
        return ResponseEntity.ok(resultado);
    }

    @PatchMapping("/{id}/completar")
    public ResponseEntity<String> completar(@PathVariable Long id) {
        String resultado = citaService.completar(id);
        if (resultado.equals("Cita no encontrada"))
            return ResponseEntity.notFound().build();
        if (resultado.contains("No se puede"))
            return ResponseEntity.badRequest().body(resultado);
        return ResponseEntity.ok(resultado);
    }

    @PatchMapping("/{id}/no-asistio")
    public ResponseEntity<String> noAsistio(@PathVariable Long id) {
        String resultado = citaService.noAsistio(id);
        if (resultado.equals("Cita no encontrada"))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(resultado);
    }
}
