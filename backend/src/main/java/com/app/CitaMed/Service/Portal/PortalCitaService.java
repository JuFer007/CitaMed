package com.app.CitaMed.Service.Portal;
import com.app.CitaMed.DTO.DiagnosticoDTO;
import com.app.CitaMed.DTO.PagoEstadoDTO;
import com.app.CitaMed.DTO.PortalCitaDTO;
import com.app.CitaMed.DTO.PortalPagoDTO;
import com.app.CitaMed.DTO.PortalReservaRequest;
import com.app.CitaMed.DTO.SlotDisponibleDTO;
import com.app.CitaMed.Enums.EstadoCita;
import com.app.CitaMed.Enums.EstadoPago;
import com.app.CitaMed.Enums.MetodoPago;
import com.app.CitaMed.Model.Administrativo.Pago;
import com.app.CitaMed.Model.Agenda.Cita;
import com.app.CitaMed.Model.Medico.Diagnostico;
import com.app.CitaMed.Model.Medico.Especialidad;
import com.app.CitaMed.Model.Medico.Medico;
import com.app.CitaMed.Model.Paciente.Paciente;
import com.app.CitaMed.Repository.Agenda.CitaRepository;
import com.app.CitaMed.Repository.Administrativo.PagoRepository;
import com.app.CitaMed.Repository.Medico.DiagnosticoRepository;
import com.app.CitaMed.Repository.Medico.EspecialidadRepository;
import com.app.CitaMed.Repository.Medico.MedicoRepository;
import com.app.CitaMed.Repository.Paciente.PacienteRepository;
import com.app.CitaMed.Service.Agenda.CitaService;
import com.app.CitaMed.Service.Administrativo.ReservaService;
import com.app.CitaMed.Service.MicroServicios.EmailService;
import com.app.CitaMed.Util.HorarioValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class PortalCitaService {

    private final CitaRepository citaRepository;
    private final DiagnosticoRepository diagnosticoRepository;
    private final PagoRepository pagoRepository;
    private final CitaService citaService;
    private final ReservaService reservaService;
    private final EspecialidadRepository especialidadRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final PortalNotificacionService portalNotificacionService;
    private final EmailService emailService;

    @Transactional(readOnly = true)
    public List<PortalCitaDTO> obtenerProximas(Long pacienteId) {
        List<Cita> citas = citaRepository
                .findByPacienteIdAndEstadoAndFechaHoraAfterOrderByFechaHoraAsc(
                        pacienteId, EstadoCita.PROGRAMADA, LocalDateTime.now());
        return citas.stream().map(this::toPortalCitaDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PortalCitaDTO> obtenerHistorial(Long pacienteId) {
        List<Cita> citas = citaRepository
                .findByPacienteIdAndFechaHoraBeforeOrderByFechaHoraDesc(
                        pacienteId, LocalDateTime.now());
        if (citas.isEmpty()) {
            citas = citaRepository.findByPacienteIdOrderByFechaHoraDesc(pacienteId);
        }
        return citas.stream()
                .filter(c -> c.getEstado() != EstadoCita.PROGRAMADA
                        || c.getFechaHora().isBefore(LocalDateTime.now()))
                .map(this::toPortalCitaDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PortalCitaDTO obtenerDetalle(Long citaId, Long pacienteId) {
        Cita cita = citaRepository.findById(citaId)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        if (!cita.getPaciente().getId().equals(pacienteId)) {
            throw new RuntimeException("La cita no pertenece al paciente");
        }
        return toPortalCitaDTO(cita);
    }

    @Transactional
    public void cancelar(Long citaId, Long pacienteId) {
        Cita cita = citaRepository.findById(citaId)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        if (!cita.getPaciente().getId().equals(pacienteId)) {
            throw new RuntimeException("La cita no pertenece al paciente");
        }
        String resultado = citaService.cancelar(citaId);
        if (resultado.contains("no se puede") || resultado.contains("ya está")) {
            throw new RuntimeException(resultado);
        }
    }

    @Transactional(readOnly = true)
    public DiagnosticoDTO obtenerDiagnostico(Long citaId, Long pacienteId) {
        Cita cita = citaRepository.findById(citaId)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        if (!cita.getPaciente().getId().equals(pacienteId)) {
            throw new RuntimeException("La cita no pertenece al paciente");
        }
        Diagnostico diagnostico = diagnosticoRepository.findByCitaId(citaId)
                .orElseThrow(() -> new RuntimeException("No hay diagnóstico registrado para esta cita"));

        DiagnosticoDTO dto = new DiagnosticoDTO();
        dto.setCitaId(citaId);
        dto.setEnfermedad(diagnostico.getEnfermedad());
        dto.setDescripcion(diagnostico.getDescripcion());
        dto.setReceta(diagnostico.getReceta());
        dto.setIndicaciones(diagnostico.getIndicaciones());
        return dto;
    }

    @Transactional(readOnly = true)
    public List<PortalPagoDTO> obtenerPagos(Long pacienteId) {
        List<Pago> pagos = pagoRepository.findByPacienteId(pacienteId);
        return pagos.stream().map(p -> {
            Cita cita = p.getCita();
            return PortalPagoDTO.builder()
                    .id(p.getId())
                    .citaId(cita.getId())
                    .concepto(cita.getMotivoConsulta())
                    .medico("DR. " + cita.getMedico().getNombre() + " " + cita.getMedico().getApellidoPaterno())
                    .especialidad(cita.getMedico().getEspecialidad().getNombre())
                    .fecha(p.getFechaPago() != null ? p.getFechaPago().toString() : null)
                    .hora(cita.getFechaHora() != null ? cita.getFechaHora().format(DateTimeFormatter.ofPattern("HH:mm")) : null)
                    .monto(p.getMonto())
                    .metodoPago(p.getMetodoPago() != null ? p.getMetodoPago().name() : null)
                    .estado(p.getEstado().name())
                    .dniPaciente(cita.getPaciente().getDni())
                    .pacienteNombre(cita.getPaciente().getNombre() + " " + cita.getPaciente().getApellidoPaterno() + " " + cita.getPaciente().getApellidoMaterno())
                    .build();
        }).collect(Collectors.toList());
    }

    public List<Especialidad> obtenerEspecialidades() {
        return especialidadRepository.findAll();
    }

    public List<Medico> obtenerMedicosPorEspecialidad(Long especialidadId) {
        return medicoRepository.findByEspecialidadIdAndActivoTrue(especialidadId);
    }

    public List<SlotDisponibleDTO> obtenerSlotsDisponibles(Long especialidadId, String fecha) {
        return reservaService.obtenerSlotsDisponibles(especialidadId, fecha);
    }

    @Transactional
    public void reservar(Long pacienteId, PortalReservaRequest request) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        Medico medico = medicoRepository.findById(request.getMedicoId())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        if (!medico.isActivo())
            throw new RuntimeException("El médico no está activo");

        if (medico.getConsultorio() == null || !medico.getConsultorio().getId().equals(request.getConsultorioId()))
            throw new RuntimeException("El médico no tiene el consultorio seleccionado");

        LocalDateTime fechaHora = LocalDateTime.parse(request.getFechaHora(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));

        HorarioValidator.validar(fechaHora);

        LocalDateTime fin = fechaHora.plusHours(1);
        if (citaRepository.countOverlapByMedico(medico.getId(), EstadoCita.CANCELADA, fechaHora.minusHours(1), fin) > 0)
            throw new RuntimeException("El horario seleccionado ya no está disponible");

        if (citaRepository.countOverlapByPaciente(paciente.getId(), EstadoCita.CANCELADA, fechaHora.minusHours(1), fin) > 0)
            throw new RuntimeException("Ya tienes una cita en ese horario");

        Cita cita = new Cita();
        cita.setPaciente(paciente);
        cita.setMedico(medico);
        cita.setConsultorio(medico.getConsultorio());
        cita.setFechaHora(fechaHora);
        cita.setMotivoConsulta(request.getMotivoConsulta());
        cita.setEstado(EstadoCita.PROGRAMADA);
        citaRepository.save(cita);

        Pago pago = new Pago();
        pago.setCita(cita);
        pago.setMonto(medico.getEspecialidad().getPrecio());
        pago.setMetodoPago(MetodoPago.STRIPE);
        pago.setEstado(EstadoPago.PENDIENTE);
        pago.setFechaPago(null);
        pagoRepository.save(pago);

        portalNotificacionService.crearNotificacion(
                paciente,
                "Cita reservada con " + medico.getNombre() + " " + medico.getApellidoPaterno()
                        + " para el " + fechaHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                "CITA",
                cita.getId(),
                "citas"
        );

        try {
            emailService.enviarConfirmacion(cita);
        } catch (Exception ignored) {}
    }

    private PortalCitaDTO toPortalCitaDTO(Cita cita) {
        DiagnosticoDTO diagnosticoDTO = null;
        Diagnostico diagnostico = diagnosticoRepository.findByCitaId(cita.getId()).orElse(null);
        if (diagnostico != null) {
            DiagnosticoDTO d = new DiagnosticoDTO();
            d.setCitaId(cita.getId());
            d.setEnfermedad(diagnostico.getEnfermedad());
            d.setDescripcion(diagnostico.getDescripcion());
            d.setReceta(diagnostico.getReceta());
            d.setIndicaciones(diagnostico.getIndicaciones());
            diagnosticoDTO = d;
        }

        PagoEstadoDTO pagoDTO = null;
        Pago pago = pagoRepository.findByCitaId(cita.getId()).orElse(null);
        if (pago != null) {
            pagoDTO = PagoEstadoDTO.builder()
                    .id(pago.getId())
                    .monto(pago.getMonto())
                    .metodoPago(pago.getMetodoPago() != null ? pago.getMetodoPago().name() : null)
                    .estado(pago.getEstado() != null ? pago.getEstado().name() : null)
                    .fechaPago(pago.getFechaPago() != null ? pago.getFechaPago().toString() : null)
                    .build();
        }

        LocalDateTime fh = cita.getFechaHora();
        return PortalCitaDTO.builder()
                .id(cita.getId())
                .fechaHora(fh)
                .fecha(fh.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .hora(fh.format(DateTimeFormatter.ofPattern("HH:mm")))
                .medicoNombre(cita.getMedico().getNombre())
                .medicoApellido(cita.getMedico().getApellidoPaterno())
                .medico(cita.getMedico().getNombre() + " " + cita.getMedico().getApellidoPaterno())
                .especialidad(cita.getMedico().getEspecialidad().getNombre())
                .consultorio(cita.getConsultorio().getNumero())
                .estado(cita.getEstado().name())
                .motivoConsulta(cita.getMotivoConsulta())
                .diagnostico(diagnosticoDTO)
                .pago(pagoDTO)
                .build();
    }
}