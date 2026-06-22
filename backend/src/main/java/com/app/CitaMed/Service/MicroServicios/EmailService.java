package com.app.CitaMed.Service.MicroServicios;
import com.app.CitaMed.Model.Agenda.Cita;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
@Component

public class EmailService {
    private static final Logger LOG = Logger.getLogger(EmailService.class.getName());

    private final TemplateEngine templateEngine;

    @Value("${brevo.api.key}")
    private String apiKey;

    @Value("${brevo.url}")
    private String url;

    private final DateTimeFormatter fmtFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final DateTimeFormatter fmtHora = DateTimeFormatter.ofPattern("hh:mm a");

    // ── Método sobrecargado con Strings planos (sin entidades JPA) ──
    public void enviarConfirmacion(String nombre, String doctor, String especialidad, String email, String fecha, String hora) {
        try {
            Context context = new Context();
            context.setVariable("nombre", nombre);
            context.setVariable("doctor", doctor);
            context.setVariable("especialidad", especialidad);
            context.setVariable("fecha", fecha);
            context.setVariable("hora", hora);

            String html = templateEngine.process("email/confirmacion", context);
            enviarCorreo(email, "Cita Confirmada - CitaMed", html);
        } catch (Exception e) {
            LOG.warning("Error enviando confirmacion por correo: " + e.getMessage());
        }
    }

    // ── Métodos originales con Cita (se mantienen para otras partes del sistema) ──
    public void enviarConfirmacion(Cita cita) {
        enviarConfirmacion(
                cita.getPaciente().getNombre(),
                cita.getMedico().getNombre() + " " + cita.getMedico().getApellidoPaterno(),
                cita.getMedico().getEspecialidad().getNombre(),
                cita.getPaciente().getEmail(),
                cita.getFechaHora().format(fmtFecha),
                cita.getFechaHora().format(fmtHora)
        );
    }

    public void enviarReprogramacion(Cita cita, java.time.LocalDateTime fechaAnterior) {
        try {
            Context context = new Context();
            context.setVariable("nombre", cita.getPaciente().getNombre());
            context.setVariable("doctor", cita.getMedico().getNombre() + " " + cita.getMedico().getApellidoPaterno());
            context.setVariable("especialidad", cita.getMedico().getEspecialidad().getNombre());
            context.setVariable("fechaAnterior", fechaAnterior.format(fmtFecha));
            context.setVariable("horaAnterior", fechaAnterior.format(fmtHora));
            context.setVariable("nuevaFecha", cita.getFechaHora().format(fmtFecha));
            context.setVariable("nuevaHora", cita.getFechaHora().format(fmtHora));

            String html = templateEngine.process("email/reprogramacion", context);
            enviarCorreo(cita.getPaciente().getEmail(), "Cita Reprogramada - CitaMed", html);
        } catch (Exception e) {
            LOG.warning("Error enviando reprogramacion por correo: " + e.getMessage());
        }
    }

    public void enviarCancelacion(Cita cita) {
        try {
            Context context = new Context();
            context.setVariable("nombre", cita.getPaciente().getNombre());
            context.setVariable("doctor", cita.getMedico().getNombre() + " " + cita.getMedico().getApellidoPaterno());
            context.setVariable("especialidad", cita.getMedico().getEspecialidad().getNombre());
            context.setVariable("fecha", cita.getFechaHora().format(fmtFecha));
            context.setVariable("hora", cita.getFechaHora().format(fmtHora));

            String html = templateEngine.process("email/cancelacion", context);
            enviarCorreo(cita.getPaciente().getEmail(), "Cita Cancelada - CitaMed", html);
        } catch (Exception e) {
            LOG.warning("Error enviando cancelacion por correo: " + e.getMessage());
        }
    }

    public void enviarCorreo(String destino, String asunto, String html) {
        try {
            WebClient.builder()
                    .baseUrl(url)
                    .defaultHeader("api-key", apiKey)
                    .build()
                    .post()
                    .bodyValue(Map.of(
                            "sender", Map.of(
                                    "name", "CitaMed",
                                    "email", "juniorfernandozumaetagolac@gmail.com"
                            ),
                            "to", List.of(Map.of("email", destino)),
                            "subject", asunto,
                            "htmlContent", html
                    ))
                    .retrieve()
                    .bodyToMono(String.class)
                    .timeout(Duration.ofSeconds(10))
                    .block();
        } catch (Exception e) {
            LOG.warning("Error enviando correo a " + destino + ": " + e.getMessage());
        }
    }
}
