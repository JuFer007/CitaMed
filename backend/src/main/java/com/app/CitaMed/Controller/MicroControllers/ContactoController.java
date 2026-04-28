package com.app.CitaMed.Controller.MicroControllers;
import com.app.CitaMed.DTO.ContactoDTO;
import com.app.CitaMed.Service.MicroServicios.ContactoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacto")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor

public class ContactoController {
    private final ContactoService contactoService;

    @PostMapping
    public ResponseEntity<String> contacto(@RequestBody ContactoDTO dto) {
        String resultado = contactoService.procesarContacto(dto);
        if (resultado.contains("Error"))
            return ResponseEntity.status(500).body(resultado);
        return ResponseEntity.ok(resultado);
    }
}
