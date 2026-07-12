package com.app.CitaMed.Controller.Util;
import com.app.CitaMed.DTO.DniLookupDTO;
import com.app.CitaMed.DTO.MedicoPerfilDTO;
import com.app.CitaMed.DTO.ReservaDTO;
import com.app.CitaMed.DTO.SlotDisponibleDTO;
import com.app.CitaMed.DTO.ReniecDataDTO;
import com.app.CitaMed.DTO.TestimonioPublicoDTO;
import com.app.CitaMed.Model.Medico.Especialidad;
import com.app.CitaMed.Model.Medico.Medico;
import com.app.CitaMed.Model.Paciente.Paciente;
import com.app.CitaMed.Repository.Paciente.PacienteRepository;
import com.app.CitaMed.Service.Administrativo.ReservaService;
import com.app.CitaMed.Service.Medico.EspecialidadService;
import com.app.CitaMed.Service.Medico.MedicoService;
import com.app.CitaMed.Service.MicroServicios.ReniecService;
import com.app.CitaMed.Service.Portal.PortalTestimonioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/lading")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class LadingPageController {

    private final MedicoService medicoService;
    private final EspecialidadService especialidadService;
    private final ReservaService reservaService;
    private final PacienteRepository pacienteRepository;
    private final ReniecService reniecService;
    private final PortalTestimonioService portalTestimonioService;

    @GetMapping("/random")
    public ResponseEntity<?> obtenerMedicoRandom() {
        MedicoPerfilDTO medico = medicoService.medicoAlAzarPerfil();
        if (medico == null)
            return ResponseEntity.status(404).body("No hay médicos disponibles");
        return ResponseEntity.ok(medico);
    }

    @GetMapping("/testimonios")
    public ResponseEntity<List<TestimonioPublicoDTO>> listarTestimonios() {
        return ResponseEntity.ok(portalTestimonioService.obtenerTestimoniosPublicos());
    }

    @GetMapping("/medicos")
    public ResponseEntity<List<Medico>> listarMedicos() {
        return ResponseEntity.ok(medicoService.findAllActivos());
    }

    @GetMapping("/especialidades")
    public ResponseEntity<List<Especialidad>> listarEspecialidades() {
        return ResponseEntity.ok(especialidadService.findAll());
    }

    @GetMapping("/slots")
    public ResponseEntity<List<SlotDisponibleDTO>> obtenerSlots(
            @RequestParam Long especialidadId,
            @RequestParam String fecha) {
        return ResponseEntity.ok(reservaService.obtenerSlotsDisponibles(especialidadId, fecha));
    }

    @PostMapping("/reserva")
    public ResponseEntity<String> procesarReserva(@RequestBody @Valid ReservaDTO dto) {
        try {
            String resultado = reservaService.procesarReserva(dto);
            return ResponseEntity.ok(resultado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/consulta-dni/{dni}")
    public ResponseEntity<?> consultarDni(@PathVariable String dni) {
        if (dni == null || dni.length() != 8) {
            return ResponseEntity.badRequest().body("DNI inválido");
        }

        Paciente paciente = pacienteRepository.findByDni(dni);
        if (paciente != null) {
            boolean activo = paciente.getActivo() != null && paciente.getActivo();
            DniLookupDTO dto = DniLookupDTO.builder()
                    .dni(paciente.getDni())
                    .nombre(paciente.getNombre())
                    .apellidoPaterno(paciente.getApellidoPaterno())
                    .apellidoMaterno(paciente.getApellidoMaterno())
                    .telefono(paciente.getTelefono())
                    .email(paciente.getEmail())
                    .direccion(paciente.getDireccion())
                    .fechaNacimiento(paciente.getFechaNacimiento())
                    .genero(paciente.getGenero())
                    .grupoSanguineo(paciente.getGrupoSanguineo())
                    .existeLocal(activo)
                    .build();
            return ResponseEntity.ok(dto);
        }

        ReniecDataDTO reniec = reniecService.consultarDni(dni);
        if (reniec != null) {
            DniLookupDTO dto = DniLookupDTO.builder()
                    .dni(reniec.getDni())
                    .nombre(reniec.getNombres())
                    .apellidoPaterno(reniec.getApellidoPaterno())
                    .apellidoMaterno(reniec.getApellidoMaterno())
                    .existeLocal(false)
                    .build();
            return ResponseEntity.ok(dto);
        }

        return ResponseEntity.status(404).body("No se encontraron datos para el DNI ingresado");
    }
}
