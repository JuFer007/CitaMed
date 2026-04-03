package com.app.DocCenter.Service.Agenda;
import com.app.DocCenter.DTO.CitaDTO;
import com.app.DocCenter.Enums.EstadoCita;
import com.app.DocCenter.Model.Administrativo.Consultorio;
import com.app.DocCenter.Model.Agenda.Cita;
import com.app.DocCenter.Model.Medico.Medico;
import com.app.DocCenter.Model.Paciente.Paciente;
import com.app.DocCenter.Repository.Administrativo.ConsultorioRepository;
import com.app.DocCenter.Repository.Agenda.CitaRepository;
import com.app.DocCenter.Repository.Medico.MedicoRepository;
import com.app.DocCenter.Repository.Paciente.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor

public class CitaService {
    private final CitaRepository citaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;
    private final ConsultorioRepository consultorioRepository;

    public List<Cita> findAll() {
        return citaRepository.findAll();
    }

    public Cita findById(Long id) {
        return citaRepository.findById(id).orElse(null);
    }

    public List<Cita> findByMedico(Long medicoId) {
        return citaRepository.findByMedicoId(medicoId);
    }

    public List<Cita> findByPaciente(Long pacienteId) {
        return citaRepository.findByPacienteId(pacienteId);
    }

    public String save(CitaDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId()).orElse(null);
        if (paciente == null) return "Paciente no encontrado";

        Medico medico = medicoRepository.findById(dto.getMedicoId()).orElse(null);
        if (medico == null) return "Médico no encontrado";

        if (!medico.isActivo()) return "El médico no está activo";

        Consultorio consultorio = consultorioRepository.findById(dto.getConsultorioId()).orElse(null);
        if (consultorio == null) return "Consultorio no encontrado";

        if (!consultorio.isDisponible()) return "El consultorio no está disponible";

        if (citaRepository.existsByMedicoIdAndFechaHoraAndEstadoNot(dto.getMedicoId(), dto.getFechaHora(), EstadoCita.CANCELADA))
            return "El médico ya tiene una cita en ese horario";

        if (citaRepository.existsByPacienteIdAndFechaHoraAndEstadoNot(dto.getPacienteId(), dto.getFechaHora(), EstadoCita.CANCELADA))
            return "El paciente ya tiene una cita en ese horario";

        Cita cita = new Cita();
        cita.setPaciente(paciente);
        cita.setMedico(medico);
        cita.setConsultorio(consultorio);
        cita.setFechaHora(dto.getFechaHora());
        cita.setMotivoConsulta(dto.getMotivoConsulta());
        cita.setEstado(EstadoCita.PROGRAMADA);
        citaRepository.save(cita);
        return "Cita registrada correctamente";
    }

    public String cancelar(Long id) {
        Cita cita = citaRepository.findById(id).orElse(null);
        if (cita == null) return "Cita no encontrada";
        if (cita.getEstado() == EstadoCita.ATENDIDA)
            return "No se puede cancelar una cita ya atendida";
        cita.setEstado(EstadoCita.CANCELADA);
        citaRepository.save(cita);
        return "Cita cancelada correctamente";
    }

    public String completar(Long id) {
        Cita cita = citaRepository.findById(id).orElse(null);
        if (cita == null) return "Cita no encontrada";
        if (cita.getEstado() == EstadoCita.CANCELADA)
            return "No se puede completar una cita cancelada";
        cita.setEstado(EstadoCita.ATENDIDA);
        citaRepository.save(cita);
        return "Cita completada correctamente";
    }

    public String noAsistio(Long id) {
        Cita cita = citaRepository.findById(id).orElse(null);
        if (cita == null) return "Cita no encontrada";
        cita.setEstado(EstadoCita.NO_ASISTIO);
        citaRepository.save(cita);
        return "Cita marcada como no asistida";
    }
}
