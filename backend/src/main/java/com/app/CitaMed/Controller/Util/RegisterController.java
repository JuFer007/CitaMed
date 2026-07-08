package com.app.CitaMed.Controller.Util;
import com.app.CitaMed.DTO.RegisterRequest;
import com.app.CitaMed.Service.Portal.PortalAuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/portal")
@AllArgsConstructor

public class RegisterController {

    private final PortalAuthService portalAuthService;

    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@Valid @RequestBody RegisterRequest request) {
        try {
            portalAuthService.registrar(request);
            return ResponseEntity.ok(Map.of("mensaje", "Cuenta creada exitosamente. Ahora puedes iniciar sesión."));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
