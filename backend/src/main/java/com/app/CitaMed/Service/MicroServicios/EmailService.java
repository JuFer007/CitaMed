package com.app.CitaMed.Service.MicroServicios;
import com.app.CitaMed.Model.Agenda.Cita;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
@Component

public class EmailService {
    private final TemplateEngine templateEngine;

    @Value("${brevo.api.key}")
    private String apiKey;

    @Value("${brevo.url}")
    private String url;

    private final DateTimeFormatter fmtFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final DateTimeFormatter fmtHora = DateTimeFormatter.ofPattern("hh:mm a");

    @Async
    public void enviarConfirmacion(Cita cita) {
        Context context = new Context();

        context.setVariable("nombre", cita.getPaciente().getNombre());
        context.setVariable("doctor", cita.getMedico().getNombre() + " " + cita.getMedico().getApellidoPaterno());
        context.setVariable("especialidad", cita.getMedico().getEspecialidad().getNombre());
        context.setVariable("fecha", cita.getFechaHora().format(fmtFecha));
        context.setVariable("hora", cita.getFechaHora().format(fmtHora));

        String html = templateEngine.process("email/confirmacion", context);

        enviarCorreo(
                cita.getPaciente().getEmail(),
                "Cita Confirmada - CitaMed",
                html
        );
    }

    @Async
    public void enviarReprogramacion(Cita cita, java.time.LocalDateTime fechaAnterior) {
        Context context = new Context();

        context.setVariable("nombre", cita.getPaciente().getNombre());
        context.setVariable("doctor", cita.getMedico().getNombre() + " " + cita.getMedico().getApellidoPaterno());
        context.setVariable("especialidad", cita.getMedico().getEspecialidad().getNombre());
        context.setVariable("fechaAnterior", fechaAnterior.format(fmtFecha));
        context.setVariable("horaAnterior", fechaAnterior.format(fmtHora));
        context.setVariable("nuevaFecha", cita.getFechaHora().format(fmtFecha));
        context.setVariable("nuevaHora", cita.getFechaHora().format(fmtHora));

        String html = templateEngine.process("email/reprogramacion", context);

        enviarCorreo(
                cita.getPaciente().getEmail(),
                "Cita Reprogramada - CitaMed",
                html
        );
    }

    @Async
    public void enviarCancelacion(Cita cita) {
        Context context = new Context();

        context.setVariable("nombre", cita.getPaciente().getNombre());
        context.setVariable("doctor", cita.getMedico().getNombre() + " " + cita.getMedico().getApellidoPaterno());
        context.setVariable("especialidad", cita.getMedico().getEspecialidad().getNombre());
        context.setVariable("fecha", cita.getFechaHora().format(fmtFecha));
        context.setVariable("hora", cita.getFechaHora().format(fmtHora));

        String html = templateEngine.process("email/cancelacion", context);

        enviarCorreo(
                cita.getPaciente().getEmail(),
                "Cita Cancelada - CitaMed",
                html
        );
    }

    public void enviarCorreo(String destino, String asunto, String html) {
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
                .block();
    }
}
