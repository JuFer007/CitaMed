package com.app.CitaMed.Service.MicroServicios;
import com.app.CitaMed.DTO.ContactoDTO;
import com.app.CitaMed.Model.Agenda.HorarioMedico;
import com.app.CitaMed.Model.Medico.Especialidad;
import com.app.CitaMed.Model.Medico.Medico;
import com.app.CitaMed.Repository.Agenda.HorarioMedicoRepository;
import com.app.CitaMed.Repository.Medico.EspecialidadRepository;
import com.app.CitaMed.Repository.Medico.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class ContactoService {
    private final MailerSendService mailerSendService;
    private final EspecialidadRepository especialidadRepository;
    private final MedicoRepository medicoRepository;
    private final HorarioMedicoRepository horarioMedicoRepository;

    private static final List<String> KEYWORDS_EMERGENCIA = List.of("emergencia", "urgente", "urgencia");
    private static final List<String> KEYWORDS_CITA = List.of("cita", "reservar", "agendar", "turno");
    private static final List<String> KEYWORDS_HORARIO = List.of("horario", "horarios", "disponible", "disponibles", "atienden", "cuando");
    private static final List<String> KEYWORDS_ESPECIALIDADES = List.of(
            "cardiolog", "pediatr", "ginecolog", "traumatolog", "neurolog",
            "dermatolog", "endocrinolog", "gastroenterolog", "oftalmolog",
            "medicina general", "doctor", "médico", "especialidad"
    );

    public String procesarContacto(ContactoDTO dto) {
        if (dto.getNombre() == null || dto.getEmail() == null || dto.getMensaje() == null) {
            return "Faltan datos requeridos";
        }

        String texto = dto.getMensaje().toLowerCase();
        String asunto;
        String cuerpoHtml;
        String cuerpoTexto;

        //Para emergencias
        if (contieneKeyword(texto, KEYWORDS_EMERGENCIA)) {
            asunto = "Emergencia - CitaMed";
            cuerpoTexto = String.format(
                    "Hola %s,\n\nHemos recibido tu mensaje de emergencia. " +
                            "Por favor, llama directamente a nuestra clínica al +51 987 654 321 para asistencia inmediata.\n\nCitaMed", dto.getNombre());
            cuerpoHtml = formatearHtml(cuerpoTexto);
        }
        //Pregunta para especialidad o medicos
        else if (contieneKeyword(texto, KEYWORDS_ESPECIALIDADES) || contieneKeyword(texto, KEYWORDS_HORARIO)) {
            String infoReal = construirInfoEspecialidades(texto);
            cuerpoTexto = String.format(
                    "Hola %s,\n\nGracias por contactarnos. Aquí tienes la información disponible:\n\n%s\n\n" +
                            "Para agendar una cita, responde este correo o llámanos.\n\nSaludos,\nCitaMed", dto.getNombre(), infoReal);
            asunto = "Información de especialidades y horarios - CitaMed";
            cuerpoHtml = formatearHtml(cuerpoTexto);
        }
        //Para agendar una cita
        else if (contieneKeyword(texto, KEYWORDS_CITA)) {
            String todasEspecialidades = especialidadRepository.findAll().stream()
                    .map(Especialidad::getNombre)
                    .collect(Collectors.joining(", "));
            cuerpoTexto = String.format(
                    "Hola %s,\n\nGracias por querer agendar una cita en CitaMed.\n\n" +
                            "Contamos con las siguientes especialidades: %s.\n\n" +
                            "Puedes agendar tu cita respondiendo este correo o llamando al +51 987 654 321.\n\nSaludos,\nCitaMed",
                    dto.getNombre(), todasEspecialidades);
            asunto = "Solicitud de cita - CitaMed";
            cuerpoHtml = formatearHtml(cuerpoTexto);
        }
        //Defecto
        else {
            cuerpoTexto = String.format(
                    "Hola %s,\n\nGracias por contactarnos en CitaMed. Hemos recibido tu mensaje:\n\n\"%s\"\n\n" +
                            "Nuestro equipo se pondrá en contacto contigo lo antes posible.\n\nSaludos,\nCitaMed",
                    dto.getNombre(), dto.getMensaje());
            asunto = "Respuesta a tu consulta - CitaMed";
            cuerpoHtml = formatearHtml(cuerpoTexto);
        }

        boolean enviado = mailerSendService.enviarEmail(dto.getEmail(), dto.getNombre(), asunto, cuerpoHtml, cuerpoTexto);
        return enviado ? "Mensaje recibido y respuesta enviada correctamente" : "Error al enviar el correo";
    }

    private String construirInfoEspecialidades(String texto) {
        List<Especialidad> especialidades = especialidadRepository.findAll();
        StringBuilder info = new StringBuilder();

        List<Especialidad> encontradas = especialidades.stream()
                .filter(e -> texto.contains(e.getNombre().toLowerCase()) ||
                texto.contains(e.getNombre().toLowerCase().substring(0, Math.min(6, e.getNombre().length()))))
                .collect(Collectors.toList());

        if (encontradas.isEmpty()) {
            encontradas = especialidades;
            info.append("Nuestras especialidades disponibles:\n\n");
        } else {
            info.append("Información sobre la especialidad consultada:\n\n");
        }

        for (Especialidad esp : encontradas) {
            List<Medico> medicos = medicoRepository.findByEspecialidadIdAndActivoTrue(esp.getId());
            if (medicos.isEmpty()) continue;

            info.append(" ").append(esp.getNombre()).append("\n");

            for (Medico medico : medicos) {
                info.append("   Dr(a). ")
                        .append(medico.getNombre()).append(" ")
                        .append(medico.getApellidoPaterno()).append("\n");

                List<HorarioMedico> horarios = horarioMedicoRepository.findByMedicoIdAndActivoTrue(medico.getId());
                if (!horarios.isEmpty()) {
                    info.append("   Horarios:\n");
                    for (HorarioMedico h : horarios) {
                        info.append("      • ").append(h.getDia())
                                .append(": ").append(h.getHoraInicio())
                                .append(" - ").append(h.getHoraFin()).append("\n");
                    }
                } else {
                    info.append("   Sin horarios programados actualmente.\n");
                }
                info.append("\n");
            }
        }

        if (info.toString().equals(" Nuestras especialidades disponibles:\n\n") ||
                info.toString().equals(" Información sobre la especialidad consultada:\n\n")) {
            info.append("Por el momento no hay médicos activos registrados. Te contactaremos pronto.");
        }

        return info.toString();
    }

    private boolean contieneKeyword(String texto, List<String> keywords) {
        return keywords.stream().anyMatch(texto::contains);
    }

    private String formatearHtml(String texto) {
        return "<div style='font-family: Arial, sans-serif; line-height: 1.8; color: #333;'>" +
                texto.replace("\n", "<br>") +
                "</div>";
    }
}
