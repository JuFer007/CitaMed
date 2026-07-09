package com.app.CitaMed.Controller.Portal;
import com.app.CitaMed.DTO.PerfilRequest;
import com.app.CitaMed.DTO.PortalPerfilDTO;
import com.app.CitaMed.DTO.PortalReservaRequest;
import com.app.CitaMed.Model.Paciente.Paciente;
import com.app.CitaMed.Repository.Paciente.PacienteRepository;
import com.app.CitaMed.Service.Administrativo.UsuarioService;
import com.app.CitaMed.Service.Portal.PortalAuthService;
import com.app.CitaMed.Service.Portal.PortalCitaService;
import com.app.CitaMed.Service.Portal.PortalHistorialService;
import com.app.CitaMed.Service.Portal.PortalNotificacionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/portal")
@AllArgsConstructor

public class PortalController {

    private final PortalAuthService portalAuthService;
    private final PortalCitaService portalCitaService;
    private final PortalHistorialService portalHistorialService;
    private final PortalNotificacionService portalNotificacionService;
    private final UsuarioService usuarioService;
    private final PacienteRepository pacienteRepository;

    @GetMapping("/perfil")
    public ResponseEntity<?> obtenerPerfil(Authentication auth) {
        try {
            Long pacienteId = obtenerPacienteId(auth);
            PortalPerfilDTO perfil = portalAuthService.obtenerPerfil(pacienteId);
            return ResponseEntity.ok(perfil);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/perfil")
    public ResponseEntity<?> actualizarPerfil(
            @Valid @RequestBody PerfilRequest request,
            Authentication auth) {
        try {
            Long pacienteId = obtenerPacienteId(auth);
            portalAuthService.actualizarPerfil(pacienteId, request);
            return ResponseEntity.ok(Map.of("mensaje", "Perfil actualizado correctamente"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/citas")
    public ResponseEntity<?> obtenerProximasCitas(Authentication auth) {
        try {
            Long pacienteId = obtenerPacienteId(auth);
            return ResponseEntity.ok(portalCitaService.obtenerProximas(pacienteId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/citas/historial")
    public ResponseEntity<?> obtenerHistorialCitas(Authentication auth) {
        try {
            Long pacienteId = obtenerPacienteId(auth);
            return ResponseEntity.ok(portalCitaService.obtenerHistorial(pacienteId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/citas/{id}")
    public ResponseEntity<?> obtenerDetalleCita(@PathVariable Long id, Authentication auth) {
        try {
            Long pacienteId = obtenerPacienteId(auth);
            return ResponseEntity.ok(portalCitaService.obtenerDetalle(id, pacienteId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PatchMapping("/citas/{id}/cancelar")
    public ResponseEntity<?> cancelarCita(@PathVariable Long id, Authentication auth) {
        try {
            Long pacienteId = obtenerPacienteId(auth);
            portalCitaService.cancelar(id, pacienteId);
            return ResponseEntity.ok(Map.of("mensaje", "Cita cancelada exitosamente"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/diagnosticos/{citaId}")
    public ResponseEntity<?> obtenerDiagnostico(@PathVariable Long citaId, Authentication auth) {
        try {
            Long pacienteId = obtenerPacienteId(auth);
            return ResponseEntity.ok(portalCitaService.obtenerDiagnostico(citaId, pacienteId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/pagos")
    public ResponseEntity<?> obtenerPagos(Authentication auth) {
        try {
            Long pacienteId = obtenerPacienteId(auth);
            return ResponseEntity.ok(portalCitaService.obtenerPagos(pacienteId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/historial-clinico")
    public ResponseEntity<?> obtenerHistorialClinico(Authentication auth) {
        try {
            Long pacienteId = obtenerPacienteId(auth);
            return ResponseEntity.ok(portalHistorialService.obtenerHistorialCompleto(pacienteId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/notificaciones")
    public ResponseEntity<?> obtenerNotificaciones(Authentication auth) {
        try {
            Long pacienteId = obtenerPacienteId(auth);
            return ResponseEntity.ok(portalNotificacionService.obtenerNotificaciones(pacienteId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/notificaciones/no-leidas")
    public ResponseEntity<?> contarNoLeidas(Authentication auth) {
        try {
            Long pacienteId = obtenerPacienteId(auth);
            return ResponseEntity.ok(Map.of("cantidad", portalNotificacionService.contarNoLeidas(pacienteId)));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PatchMapping("/notificaciones/{id}/leer")
    public ResponseEntity<?> marcarComoLeida(@PathVariable Long id, Authentication auth) {
        try {
            Long pacienteId = obtenerPacienteId(auth);
            portalNotificacionService.marcarComoLeida(id, pacienteId);
            return ResponseEntity.ok(Map.of("mensaje", "Notificación marcada como leída"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PatchMapping("/notificaciones/leer-todas")
    public ResponseEntity<?> marcarTodasComoLeidas(Authentication auth) {
        try {
            Long pacienteId = obtenerPacienteId(auth);
            portalNotificacionService.marcarTodasComoLeidas(pacienteId);
            return ResponseEntity.ok(Map.of("mensaje", "Todas las notificaciones marcadas como leídas"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/especialidades")
    public ResponseEntity<?> listarEspecialidades() {
        try {
            return ResponseEntity.ok(portalCitaService.obtenerEspecialidades());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/medicos")
    public ResponseEntity<?> listarMedicos(@RequestParam Long especialidadId) {
        try {
            return ResponseEntity.ok(portalCitaService.obtenerMedicosPorEspecialidad(especialidadId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/slots")
    public ResponseEntity<?> obtenerSlots(@RequestParam Long especialidadId, @RequestParam String fecha) {
        try {
            return ResponseEntity.ok(portalCitaService.obtenerSlotsDisponibles(especialidadId, fecha));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/citas/reservar")
    public ResponseEntity<?> reservarCita(@Valid @RequestBody PortalReservaRequest request, Authentication auth) {
        try {
            Long pacienteId = obtenerPacienteId(auth);
            portalCitaService.reservar(pacienteId, request);
            return ResponseEntity.ok(Map.of("mensaje", "Cita reservada correctamente"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    private Long obtenerPacienteId(Authentication auth) {
        String username = (String) auth.getPrincipal();
        var usuario = usuarioService.findByUsuario(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Paciente paciente = pacienteRepository.findByUsuarioId(usuario.getId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado para este usuario"));

        return paciente.getId();
    }
}
