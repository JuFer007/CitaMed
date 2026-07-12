package com.app.CitaMed.Service.MicroServicios;
import com.app.CitaMed.Enums.EstadoCita;
import com.app.CitaMed.Model.Agenda.Cita;
import com.app.CitaMed.Repository.Agenda.CitaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j

public class ReminderService {

    private final CitaRepository citaRepository;
    private final EmailService emailService;
    private final TemplateEngine templateEngine;

    private static final DateTimeFormatter fmtFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter fmtHora = DateTimeFormatter.ofPattern("hh:mm a");

    @Scheduled(cron = "0 0 8 * * *")
    public void enviarRecordatorios24h() {
        log.info("Iniciando envío de recordatorios de citas para mañana...");
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime inicio = ahora.plusHours(20);
        LocalDateTime fin = ahora.plusHours(28);

        List<Cita> citas = citaRepository.findCitasProximasParaRecordatorio(
                EstadoCita.PROGRAMADA, inicio, fin);

        log.info("Se encontraron {} citas para recordar.", citas.size());

        for (Cita cita : citas) {
            try {
                enviarRecordatorio(cita);
            } catch (Exception e) {
                log.warn("Error enviando recordatorio para la cita {}: {}", cita.getId(), e.getMessage());
            }
        }

        log.info("Envío de recordatorios completado.");
    }

    private void enviarRecordatorio(Cita cita) {
        String nombrePaciente = cita.getPaciente().getNombre();
        String doctor = cita.getMedico().getNombre() + " " + cita.getMedico().getApellidoPaterno();
        String especialidad = cita.getMedico().getEspecialidad().getNombre();
        String fecha = cita.getFechaHora().format(fmtFecha);
        String hora = cita.getFechaHora().format(fmtHora);

        Context context = new Context();
        context.setVariable("nombre", nombrePaciente);
        context.setVariable("doctor", doctor);
        context.setVariable("especialidad", especialidad);
        context.setVariable("fecha", fecha);
        context.setVariable("hora", hora);

        String html = templateEngine.process("email/recordatorio", context);
        emailService.enviarCorreo(
                cita.getPaciente().getEmail(),
                "Recordatorio: Tu cita es mañana - CitaMed",
                html
        );
    }
}
