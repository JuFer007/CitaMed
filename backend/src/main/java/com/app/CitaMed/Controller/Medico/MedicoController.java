package com.app.CitaMed.Controller.Medico;
import com.app.CitaMed.DTO.MedicoDTO;
import com.app.CitaMed.Model.Medico.Medico;
import com.app.CitaMed.Service.Medico.MedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/medico")
@RequiredArgsConstructor

public class MedicoController {
    private final MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<Medico>> findAll() {
        return ResponseEntity.ok(medicoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> findById(@PathVariable Long id) {
        Medico medico = medicoService.findById(id);
        if (medico == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(medico);
    }

    @GetMapping("/especialidad/{especialidadId}")
    public ResponseEntity<List<Medico>> findByEspecialidad(@PathVariable Long especialidadId) {
        return ResponseEntity.ok(medicoService.findByEspecialidad(especialidadId));
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody MedicoDTO dto) {
        String resultado = medicoService.save(dto);
        if (!resultado.equals("Médico registrado correctamente"))
            return ResponseEntity.badRequest().body(resultado);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<String> toggleActivo(@PathVariable Long id) {
        String resultado = medicoService.toggleActivo(id);
        if (resultado.equals("Médico no encontrado"))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(resultado);
    }

    @PatchMapping("/{medicoId}/especialidad/{especialidadId}")
    public ResponseEntity<String> updateEspecialidad(
            @PathVariable Long medicoId,
            @PathVariable Long especialidadId) {
        String resultado = medicoService.updateEspecialidad(medicoId, especialidadId);
        if (resultado.equals("Médico no encontrado") || resultado.equals("Especialidad no encontrada"))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(resultado);
    }
}
