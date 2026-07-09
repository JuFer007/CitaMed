package com.app.CitaMed.Service.MicroServicios;
import com.app.CitaMed.Model.Agenda.Cita;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;
import org.springframework.scheduling.annotation.Async;

@Service
@RequiredArgsConstructor
public class EmailService {
    private static final Logger LOG = Logger.getLogger(EmailService.class.getName());

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String fromEmail;

    private final DateTimeFormatter fmtFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final DateTimeFormatter fmtHora = DateTimeFormatter.ofPattern("hh:mm a");

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

    @Async
    public void enviarRegistro(String nombre, String usuario, String email) {
        try {
            Context context = new Context();
            context.setVariable("nombre", nombre);
            context.setVariable("usuario", usuario);
            context.setVariable("email", email);
            context.setVariable("portalUrl", "http://localhost:4200/login");

            String html = templateEngine.process("email/registro", context);
            enviarCorreo(email, "Bienvenido a CitaMed - Tu cuenta ha sido creada", html);
        } catch (Exception e) {
            LOG.warning("Error enviando correo de registro: " + e.getMessage());
        }
    }

    @Async
    public void enviarRespuestaConsulta(String nombre, String email, String mensajeOriginal, String respuesta) {
        try {
            Context context = new Context();
            context.setVariable("nombre", nombre);
            context.setVariable("mensajeOriginal", mensajeOriginal);
            context.setVariable("respuesta", respuesta);

            String html = templateEngine.process("email/respuesta-consulta", context);
            enviarCorreo(email, "Respuesta a tu consulta - CitaMed", html);
        } catch (Exception e) {
            LOG.warning("Error enviando respuesta de consulta: " + e.getMessage());
        }
    }

    public void enviarCorreo(String destino, String asunto, String html) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(fromEmail);
            helper.setTo(destino);
            helper.setSubject(asunto);
            helper.setText(html, true);
            mailSender.send(message);
        } catch (Exception e) {
            LOG.warning("Error enviando correo a " + destino + ": " + e.getMessage());
        }
    }
}
