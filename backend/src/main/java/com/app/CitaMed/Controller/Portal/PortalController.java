package com.app.CitaMed.Controller.Portal;
import com.app.CitaMed.DTO.PagoRequestDTO;
import com.app.CitaMed.DTO.PerfilRequest;
import com.app.CitaMed.DTO.PortalPerfilDTO;
import com.app.CitaMed.DTO.PortalReservaRequest;
import com.app.CitaMed.DTO.RecuperarPasswordDTO;
import com.app.CitaMed.DTO.RestablecerPasswordDTO;
import com.app.CitaMed.DTO.TestimonioDTO;
import com.app.CitaMed.Model.Paciente.Paciente;
import com.app.CitaMed.Repository.Administrativo.PagoRepository;
import com.app.CitaMed.Repository.Agenda.CitaRepository;
import com.app.CitaMed.Repository.Paciente.PacienteRepository;
import com.app.CitaMed.Service.Administrativo.StripeService;
import com.app.CitaMed.Service.Administrativo.UsuarioService;
import com.app.CitaMed.Service.Portal.PortalAuthService;
import com.app.CitaMed.Service.Portal.PortalCitaService;
import com.app.CitaMed.Service.Portal.PortalHistorialService;
import com.app.CitaMed.Service.Portal.PortalNotificacionService;
import com.app.CitaMed.Service.Portal.PortalTestimonioService;
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
    private final PortalTestimonioService portalTestimonioService;
    private final UsuarioService usuarioService;
    private final PacienteRepository pacienteRepository;
    private final StripeService stripeService;
    private final PagoRepository pagoRepository;
    private final CitaRepository citaRepository;

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

    @GetMapping("/testimonios/puede-crear")
    public ResponseEntity<?> puedeCrearTestimonio(Authentication auth) {
        try {
            Long pacienteId = obtenerPacienteId(auth);
            return ResponseEntity.ok(Map.of("puedeCrear", portalTestimonioService.tieneAtendida(pacienteId)));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/testimonios")
    public ResponseEntity<?> obtenerMisTestimonios(Authentication auth) {
        try {
            Long pacienteId = obtenerPacienteId(auth);
            return ResponseEntity.ok(portalTestimonioService.obtenerMisResenas(pacienteId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/testimonios")
    public ResponseEntity<?> crearTestimonio(@Valid @RequestBody TestimonioDTO dto, Authentication auth) {
        try {
            Long pacienteId = obtenerPacienteId(auth);
            Paciente paciente = pacienteRepository.findById(pacienteId)
                    .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
            return ResponseEntity.ok(portalTestimonioService.crear(pacienteId, dto, paciente));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/testimonios/{id}")
    public ResponseEntity<?> eliminarTestimonio(@PathVariable Long id, Authentication auth) {
        try {
            Long pacienteId = obtenerPacienteId(auth);
            portalTestimonioService.eliminar(id, pacienteId);
            return ResponseEntity.ok(Map.of("mensaje", "Reseña eliminada correctamente"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/recuperar-password")
    public ResponseEntity<?> recuperarPassword(@Valid @RequestBody RecuperarPasswordDTO dto) {
        try {
            portalAuthService.solicitarRecuperarPassword(dto.getEmail());
            return ResponseEntity.ok(Map.of("mensaje", "Se ha enviado un código de recuperación a tu correo"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/restablecer-password")
    public ResponseEntity<?> restablecerPassword(@Valid @RequestBody RestablecerPasswordDTO dto) {
        try {
            portalAuthService.restablecerPassword(dto.getToken(), dto.getNuevaPassword());
            return ResponseEntity.ok(Map.of("mensaje", "Contraseña actualizada correctamente"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/pagos/intent")
    public ResponseEntity<?> crearPagoIntent(@RequestBody Map<String, Long> body, Authentication auth) {
        try {
            Long pacienteId = obtenerPacienteId(auth);
            Long citaId = body.get("citaId");

            var cita = citaRepository.findById(citaId)
                    .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
            if (!cita.getPaciente().getId().equals(pacienteId))
                throw new RuntimeException("La cita no pertenece al paciente");

            var pago = pagoRepository.findByCitaId(citaId)
                    .orElseThrow(() -> new RuntimeException("Pago no encontrado"));

            long amountInCents = (long) (pago.getMonto() * 100);
            String clientSecret = stripeService.createPaymentIntent(amountInCents, citaId.toString());
            return ResponseEntity.ok(Map.of("clientSecret", clientSecret));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Error al procesar el pago"));
        }
    }

    @PostMapping("/pagos/confirmar")
    public ResponseEntity<?> confirmarPago(@Valid @RequestBody PagoRequestDTO dto, Authentication auth) {
        try {
            Long pacienteId = obtenerPacienteId(auth);

            var cita = citaRepository.findById(dto.getCitaId())
                    .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
            if (!cita.getPaciente().getId().equals(pacienteId))
                throw new RuntimeException("La cita no pertenece al paciente");

            boolean pagado = stripeService.verifyPaymentIntent(dto.getPaymentIntentId());
            if (!pagado)
                return ResponseEntity.badRequest().body(Map.of("error", "El pago no se ha completado"));

            var pago = pagoRepository.findByCitaId(dto.getCitaId())
                    .orElseThrow(() -> new RuntimeException("Pago no encontrado"));
            pago.setEstado(com.app.CitaMed.Enums.EstadoPago.PAGADO);
            pago.setFechaPago(java.time.LocalDateTime.now());
            pagoRepository.save(pago);

            return ResponseEntity.ok(Map.of("mensaje", "Pago confirmado exitosamente"));
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