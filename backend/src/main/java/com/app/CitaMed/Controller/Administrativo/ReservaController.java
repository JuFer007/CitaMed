package com.app.CitaMed.Controller.Administrativo;
import com.app.CitaMed.DTO.ReservaDTO;
import com.app.CitaMed.DTO.SlotDisponibleDTO;
import com.app.CitaMed.Service.Administrativo.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/reserva")
@RequiredArgsConstructor

public class ReservaController {
    private final ReservaService reservaService;

    @GetMapping("/slots")
    public ResponseEntity<List<SlotDisponibleDTO>> obtenerSlots(
            @RequestParam Long especialidadId,
            @RequestParam String fecha) {
        return ResponseEntity.ok(reservaService.obtenerSlotsDisponibles(especialidadId, fecha));
    }

    @PostMapping
    public ResponseEntity<String> procesarReserva(@RequestBody ReservaDTO dto) {
        String resultado = reservaService.procesarReserva(dto);
        return ResponseEntity.ok(resultado);
    }
}
