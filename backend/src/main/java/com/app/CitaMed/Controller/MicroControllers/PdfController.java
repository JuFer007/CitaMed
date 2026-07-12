package com.app.CitaMed.Controller.MicroControllers;
import com.app.CitaMed.DTO.HistorialPdfDTO;
import com.app.CitaMed.DTO.RecetaDTO;
import com.app.CitaMed.DTO.TicketCitaDTO;
import com.app.CitaMed.Model.Agenda.Cita;
import com.app.CitaMed.Model.Medico.Diagnostico;
import com.app.CitaMed.Model.Paciente.Paciente;
import com.app.CitaMed.Service.MicroServicios.PdfService;
import com.app.CitaMed.Repository.Agenda.CitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/pdf")
@RequiredArgsConstructor

public class PdfController {
    private final PdfService service;
    private final CitaRepository citaRepository;

    @PostMapping("/ticket")
    public ResponseEntity<byte[]> ticket(@RequestBody TicketCitaDTO dto) {
        byte[] pdf = service.ticketCita(dto);
        return ResponseEntity.ok()
                .header("Content-Type", "application/pdf")
                .body(pdf);
    }

    @PostMapping("/receta")
    public ResponseEntity<byte[]> receta(@RequestBody RecetaDTO dto) {
        byte[] pdf = service.receta(dto);
        return ResponseEntity.ok()
                .header("Content-Type", "application/pdf")
                .body(pdf);
    }

    @GetMapping("/receta/cita/{citaId}")
    public ResponseEntity<?> recetaPorCita(@PathVariable Long citaId) {
        Cita cita = citaRepository.findById(citaId).orElse(null);
        if (cita == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cita no encontrada");

        Diagnostico diag = cita.getDiagnostico();
        if (diag == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La cita no tiene un diagnóstico registrado");

        Paciente p = cita.getPaciente();
        int edad = Period.between(p.getFechaNacimiento(), LocalDate.now()).getYears();

        RecetaDTO dto = new RecetaDTO();
        dto.setPaciente(p.getNombre() + " " + p.getApellidoPaterno() + " " + p.getApellidoMaterno());
        dto.setDni(p.getDni());
        dto.setEdad(String.valueOf(edad));
        String nombreMedico = "DR. " + cita.getMedico().getNombre() + " " + cita.getMedico().getApellidoPaterno() + (cita.getMedico().getApellidoMaterno() != null ? " " + cita.getMedico().getApellidoMaterno() : "");
        dto.setMedico(nombreMedico);
        dto.setEspecialidad(cita.getMedico().getEspecialidad().getNombre());
        dto.setEnfermedad(diag.getEnfermedad() != null ? diag.getEnfermedad() : "");
        dto.setDescripcion(diag.getDescripcion() != null ? diag.getDescripcion() : "");
        dto.setDiagnostico(diag.getEnfermedad() + (diag.getDescripcion() != null ? " - " + diag.getDescripcion() : ""));
        dto.setReceta(diag.getReceta() != null ? diag.getReceta() : "");
        dto.setIndicaciones(diag.getIndicaciones() != null ? diag.getIndicaciones() : "");
        dto.setFecha(cita.getFechaHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        byte[] pdf = service.receta(dto);
        return ResponseEntity.ok()
                .header("Content-Type", "application/pdf")
                .body(pdf);
    }

    @PostMapping("/historial")
    public ResponseEntity<byte[]> historial(@RequestBody HistorialPdfDTO dto) {
        byte[] pdf = service.historial(dto);
        return ResponseEntity.ok()
                .header("Content-Type", "application/pdf")
                .body(pdf);
    }
}