package com.app.CitaMed.Controller.Agenda;

import com.app.CitaMed.DTO.HorarioMedicoDTO;
import com.app.CitaMed.Model.Agenda.HorarioMedico;
import com.app.CitaMed.Service.Agenda.HorarioMedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/horarioMedico")
@RequiredArgsConstructor

public class HorarioMedicoController {
    private final HorarioMedicoService horarioMedicoService;

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<HorarioMedico>> findByMedico(@PathVariable Long medicoId) {
        return ResponseEntity.ok(horarioMedicoService.findByMedico(medicoId));
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody HorarioMedicoDTO dto) {
        String resultado = horarioMedicoService.save(dto);
        if (!resultado.equals("Horario registrado correctamente"))
            return ResponseEntity.badRequest().body(resultado);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<String> toggleActivo(@PathVariable Long id) {
        String resultado = horarioMedicoService.toggleActivo(id);
        if (resultado.equals("Horario no encontrado"))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(resultado);
    }

    @GetMapping
    public ResponseEntity<List<HorarioMedico>> findAll() {
        return ResponseEntity.ok(horarioMedicoService.findAll());
    }
}
