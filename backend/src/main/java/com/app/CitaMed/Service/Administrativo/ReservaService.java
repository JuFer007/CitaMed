package com.app.CitaMed.Service.Administrativo;
import com.app.CitaMed.DTO.ReservaDTO;
import com.app.CitaMed.DTO.SlotDisponibleDTO;
import com.app.CitaMed.Enums.*;
import com.app.CitaMed.Model.Administrativo.Consultorio;
import com.app.CitaMed.Model.Administrativo.Pago;
import com.app.CitaMed.Model.Agenda.Cita;
import com.app.CitaMed.Model.Agenda.HorarioMedico;
import com.app.CitaMed.Model.Medico.Medico;
import com.app.CitaMed.Model.Paciente.HistorialMedico;
import com.app.CitaMed.Model.Paciente.Paciente;
import com.app.CitaMed.Repository.Administrativo.ConsultorioRepository;
import com.app.CitaMed.Repository.Administrativo.PagoRepository;
import com.app.CitaMed.Repository.Agenda.CitaRepository;
import com.app.CitaMed.Repository.Agenda.HorarioMedicoRepository;
import com.app.CitaMed.Repository.Medico.MedicoRepository;
import com.app.CitaMed.Repository.Paciente.HistorialMedicoRepository;
import com.app.CitaMed.Repository.Paciente.PacienteRepository;
<<<<<<< HEAD
import com.app.CitaMed.Service.MicroServicios.EmailService;
=======
import com.app.CitaMed.Service.MicroServicios.MailerSendService;
>>>>>>> 9bdbef7091e073c5e022b427d3a629892596c822
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class ReservaService {
    private final CitaRepository citaRepository;
    private final PagoRepository pagoRepository;
    private final PacienteRepository pacienteRepository;
    private final HistorialMedicoRepository historialMedicoRepository;
    private final MedicoRepository medicoRepository;
    private final ConsultorioRepository consultorioRepository;
    private final MailerSendService mailerSendService;
    private final HorarioMedicoRepository horarioMedicoRepository;
    private final EmailService emailService;

    public List<SlotDisponibleDTO> obtenerSlotsDisponibles(Long especialidadId, String fechaStr) {
        LocalDateTime fechaBase = LocalDateTime.parse(fechaStr + "T12:00:00");
        int diaSemana = fechaBase.getDayOfWeek().getValue();
        DiaSemana dia = mapearDia(diaSemana);
        if (dia == null) return Collections.emptyList();

        List<Medico> medicos = medicoRepository.findByEspecialidadIdAndActivoTrue(especialidadId);

        List<SlotDisponibleDTO> resultado = new ArrayList<>();

        for (Medico medico : medicos) {
            List<HorarioMedico> horarios = horarioMedicoRepository
                    .findByMedicoIdAndActivoTrue(medico.getId())
                    .stream()
                    .filter(h -> h.getDia() == dia)
                    .collect(Collectors.toList());

            if (horarios.isEmpty()) continue;

            List<String> citasOcupadas = citaRepository.findByMedicoId(medico.getId())
                    .stream()
                    .filter(c -> c.getEstado() != EstadoCita.CANCELADA)
                    .map(c -> c.getFechaHora().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")))
                    .collect(Collectors.toList());

            for (HorarioMedico horario : horarios) {
                List<String> slots = generarSlots(horario.getHoraInicio(), horario.getHoraFin(), fechaStr);
                List<String> slotsLibres = slots.stream()
                        .filter(s -> !citasOcupadas.contains(s.substring(0, 16)))
                        .collect(Collectors.toList());

                if (!slotsLibres.isEmpty()) {
                    resultado.add(new SlotDisponibleDTO(
                            medico.getId(),
                            medico.getNombre(),
                            medico.getApellidoPaterno(),
                            medico.getGenero().name(),
                            medico.getEspecialidad().getNombre(),
                            horario.getConsultorio().getId(),
                            slotsLibres
                    ));
                }
            }
        }
        return resultado;
    }

    @Transactional
    public String procesarReserva(ReservaDTO dto) {
        // === 1. Crear o recuperar paciente ===
        Paciente paciente = pacienteRepository.findByDni(dto.getDni());
        if (paciente == null) {
            paciente = new Paciente();
            paciente.setNombre(dto.getNombre().toUpperCase());
            paciente.setApellidoPaterno(dto.getApellidoPaterno().toUpperCase());
            paciente.setApellidoMaterno(dto.getApellidoMaterno().toUpperCase());
            paciente.setDni(dto.getDni());
            paciente.setTelefono(dto.getTelefono());
            paciente.setEmail(dto.getEmail());
            paciente.setDireccion(dto.getDireccion());
            paciente.setFechaNacimiento(dto.getFechaNacimiento());
            paciente.setGenero(Genero.valueOf(dto.getGenero()));
            paciente.setGrupoSanguineo(GrupoSanguineo.valueOf(dto.getGrupoSanguineo()));
            pacienteRepository.save(paciente);
            HistorialMedico historial = new HistorialMedico();
            historial.setPaciente(paciente);
            historialMedicoRepository.save(historial);
        }

<<<<<<< HEAD
        Medico medico = medicoRepository.findById(dto.getMedicoId()).orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        if (!medico.isActivo()) throw new RuntimeException("El médico no está activo");

        Consultorio consultorio = consultorioRepository.findById(dto.getConsultorioId()).orElseThrow(() -> new RuntimeException("Consultorio no encontrado"));
=======
        // === 2. Validaciones ===
        Medico medico = medicoRepository.findById(dto.getMedicoId())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        if (!medico.isActivo()) throw new RuntimeException("El médico no está activo");

        Consultorio consultorio = consultorioRepository.findById(dto.getConsultorioId())
                .orElseThrow(() -> new RuntimeException("Consultorio no encontrado"));
>>>>>>> 9bdbef7091e073c5e022b427d3a629892596c822

        if (!consultorio.isDisponible()) throw new RuntimeException("El consultorio no está disponible");

        LocalDateTime fechaHora = LocalDateTime.parse(dto.getFechaHora(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));

        if (citaRepository.existsByMedicoIdAndFechaHoraAndEstadoNot(medico.getId(), fechaHora, EstadoCita.CANCELADA)) {
            throw new RuntimeException("El horario seleccionado ya no está disponible");
        }

        // === 3. Guardar Cita ===
        Cita cita = new Cita();
        cita.setPaciente(paciente);
        cita.setMedico(medico);
        cita.setConsultorio(consultorio);
        cita.setFechaHora(fechaHora);
        cita.setMotivoConsulta(dto.getMotivoConsulta());
        cita.setEstado(EstadoCita.PROGRAMADA);
        citaRepository.save(cita);

        // === 4. Registrar Pago ===
        Pago pago = new Pago();
        pago.setCita(cita);
        pago.setMonto(80.00);
        pago.setMetodoPago(MetodoPago.EFECTIVO);
        pago.setEstado(EstadoPago.PAGADO);
        pago.setFechaPago(LocalDateTime.now());
        pagoRepository.save(pago);

<<<<<<< HEAD
        emailService.enviarConfirmacion(cita);
        return "Reserva registrada correctamente";
    }

=======
        // === 5. Enviar correo de confirmación (¡Aquí está el cambio importante!) ===
        enviarCorreoConfirmacion(cita, paciente, medico);

        return "Reserva registrada correctamente";
    }

    private void enviarCorreoConfirmacion(Cita cita, Paciente paciente, Medico medico) {
        String titulo = medico.getGenero() == Genero.FEMENINO ? "Dra." : "Dr.";
        String doctor = titulo + " " + medico.getNombre() + " " + medico.getApellidoPaterno();
        String especialidad = medico.getEspecialidad().getNombre();

        String fechaFormateada = cita.getFechaHora()
                .toLocalDate()
                .format(DateTimeFormatter.ofPattern("EEEE dd 'de' MMMM yyyy", new Locale("es", "PE")));

        String horaFormateada = cita.getFechaHora()
                .toLocalTime()
                .format(DateTimeFormatter.ofPattern("hh:mm a"));

        String asunto = "Confirmación de Cita - CitaMed";

        String cuerpoTexto = String.format("""
                Hola %s,

                Tu cita ha sido reservada exitosamente.

                Detalles de tu cita:
                • Médico: %s
                • Especialidad: %s
                • Fecha: %s
                • Hora: %s
                • Consultorio: %d
                • Motivo: %s

                Por favor llega 15 minutos antes.
                Cualquier cambio, avísanos con anticipación.

                Saludos,
                Equipo CitaMed
                """,
                paciente.getNombre(),
                doctor,
                especialidad,
                fechaFormateada,
                horaFormateada,
                cita.getConsultorio().getId(),
                cita.getMotivoConsulta()
        );

        String cuerpoHtml = formatearHtml(cuerpoTexto);

        boolean enviado = mailerSendService.enviarEmail(
                paciente.getEmail(),
                paciente.getNombre(),
                asunto,
                cuerpoHtml,
                cuerpoTexto
        );

        if (!enviado) {
            System.err.println("No se pudo enviar el correo de confirmación a: " + paciente.getEmail());
        }
    }

    private String formatearHtml(String texto) {
        return "<div style='font-family: Arial, sans-serif; line-height: 1.8; color: #333; max-width: 600px; margin: 0 auto;'>" +
                texto.replace("\n", "<br>") +
                "</div>";
    }
    private List<String> generarSlots(LocalTime inicio, LocalTime fin, String fecha) {
        List<String> slots = new ArrayList<>();
        int current = inicio.getHour() * 60 + inicio.getMinute();
        int end = fin.getHour() * 60 + fin.getMinute();
        while (current + 30 <= end) {
            int h = current / 60;
            int m = current % 60;
            slots.add(String.format("%sT%02d:%02d:00", fecha, h, m));
            current += 30;
        }
        return slots;
    }

>>>>>>> 9bdbef7091e073c5e022b427d3a629892596c822
    private DiaSemana mapearDia(int dayOfWeek) {
        return switch (dayOfWeek) {
            case 1 -> DiaSemana.LUNES;
            case 2 -> DiaSemana.MARTES;
            case 3 -> DiaSemana.MIERCOLES;
            case 4 -> DiaSemana.JUEVES;
            case 5 -> DiaSemana.VIERNES;
            case 6 -> DiaSemana.SABADO;
            case 7 -> DiaSemana.DOMINGO;
            default -> null;
        };
    }

    private List<String> generarSlots(LocalTime inicio, LocalTime fin, String fecha) {
        List<String> slots = new ArrayList<>();
        int current = inicio.getHour() * 60 + inicio.getMinute();
        int end = fin.getHour() * 60 + fin.getMinute();
        while (current + 30 <= end) {
            int h = current / 60;
            int m = current % 60;
            slots.add(String.format("%sT%02d:%02d:00", fecha, h, m));
            current += 30;
        }
        return slots;
    }
}
