package com.app.CitaMed.Controller.Util;
import com.app.CitaMed.DTO.MedicoPerfilDTO;
import com.app.CitaMed.Model.Medico.Especialidad;
import com.app.CitaMed.Service.Medico.EspecialidadService;
import com.app.CitaMed.Service.Medico.MedicoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/lading")
@AllArgsConstructor

public class LadingPageController {
    private final MedicoService medicoService;
    private final EspecialidadService especialidadService;

    @GetMapping("/random")
    public ResponseEntity<?> obtenerMedicoRandom() {

        MedicoPerfilDTO medico = medicoService.medicoAlAzarPerfil();

        if (medico == null) {
            return ResponseEntity
                    .status(404)
                    .body("No hay médicos disponibles");
        }

        return ResponseEntity.ok(medico);
    }

    @GetMapping("/especialidades")
    public ResponseEntity<List<Especialidad>> listarEspecialidades() {
        return ResponseEntity.ok(especialidadService.findAll());
    }
}
