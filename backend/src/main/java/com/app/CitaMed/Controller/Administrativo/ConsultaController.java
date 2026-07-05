package com.app.CitaMed.Controller.Administrativo;
import com.app.CitaMed.DTO.ConsultaRequestDTO;
import com.app.CitaMed.Model.Administrativo.Consulta;
import com.app.CitaMed.Service.Administrativo.ConsultaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/consultas")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<String> enviar(@RequestBody @Valid ConsultaRequestDTO dto) {
        consultaService.guardar(dto.getNombre(), dto.getEmail(), dto.getMensaje());
        return ResponseEntity.ok("Consulta enviada correctamente");
    }

    @PreAuthorize("hasAnyRole('ADMIN','MEDICO','RECEPCIONISTA')")
    @GetMapping
    public ResponseEntity<List<Consulta>> listar() {
        return ResponseEntity.ok(consultaService.listarTodas());
    }

    @PreAuthorize("hasAnyRole('ADMIN','MEDICO','RECEPCIONISTA')")
    @GetMapping("/no-leidas")
    public ResponseEntity<Map<String, Long>> contarNoLeidas() {
        return ResponseEntity.ok(Map.of("count", consultaService.contarNoLeidas()));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MEDICO','RECEPCIONISTA')")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id) {
        Consulta consulta = consultaService.obtenerYMarcarLeido(id);
        if (consulta == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(consulta);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MEDICO','RECEPCIONISTA')")
    @PostMapping("/{id}/responder")
    public ResponseEntity<String> responder(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        String respuesta = body.get("respuesta");
        if (respuesta == null || respuesta.isBlank())
            return ResponseEntity.badRequest().body("La respuesta es obligatoria");

        String resultado = consultaService.responder(id, respuesta, body.get("respondidoPor"));
        if (resultado == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(resultado);
    }
}
