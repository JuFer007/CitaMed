package com.app.CitaMed.Controller.Paciente;
import com.app.CitaMed.Model.Medico.Especialidad;
import com.app.CitaMed.Model.Paciente.HistorialMedico;
import com.app.CitaMed.Service.Paciente.HistorialMedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/historialMedico")
@RequiredArgsConstructor

public class HistorialMedicoController {
    private final HistorialMedicoService historialMedicoService;

    @GetMapping("/{id}")
    public ResponseEntity<HistorialMedico> findById(@PathVariable Long id) {
        HistorialMedico h = historialMedicoService.findById(id);
        if (h == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(h);
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<HistorialMedico> findByPaciente(@PathVariable Long pacienteId) {
        HistorialMedico h = historialMedicoService.findByPaciente(pacienteId);
        if (h == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(h);
    }

    @GetMapping
    public ResponseEntity<List<HistorialMedico>> findAll() {
        return ResponseEntity.ok(historialMedicoService.findAll());
    }
}
