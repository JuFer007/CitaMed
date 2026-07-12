package com.app.CitaMed.Service.Paciente;
import com.app.CitaMed.DTO.HistorialMedicoDetalleDTO;
import com.app.CitaMed.DTO.HistorialMedicoDetalleDTO.CitaHistorialDTO;
import com.app.CitaMed.DTO.HistorialMedicoDetalleDTO.DiagnosticoHistorialDTO;
import com.app.CitaMed.DTO.HistorialMedicoDetalleDTO.PacienteInfoDTO;
import com.app.CitaMed.Model.Agenda.Cita;
import com.app.CitaMed.Model.Medico.Diagnostico;
import com.app.CitaMed.Model.Paciente.HistorialMedico;
import com.app.CitaMed.Model.Paciente.Paciente;
import com.app.CitaMed.Repository.Agenda.CitaRepository;
import com.app.CitaMed.Repository.Medico.DiagnosticoRepository;
import com.app.CitaMed.Repository.Paciente.HistorialMedicoRepository;
import com.app.CitaMed.Repository.Paciente.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class HistorialMedicoService {
    private final HistorialMedicoRepository historialMedicoRepository;
    private final PacienteRepository pacienteRepository;
    private final CitaRepository citaRepository;
    private final DiagnosticoRepository diagnosticoRepository;

    public HistorialMedico findByPaciente(Long pacienteId) {
        return historialMedicoRepository.findByPacienteId(pacienteId).orElse(null);
    }

    public HistorialMedico findById(Long id) {
        return historialMedicoRepository.findById(id).orElse(null);
    }

    public List<HistorialMedico> findAll() {
        return historialMedicoRepository.findAll();
    }

    public HistorialMedicoDetalleDTO obtenerHistorialCompleto(Long pacienteId) {
        Paciente paciente = pacienteRepository.findById(pacienteId).orElse(null);
        if (paciente == null) return null;

        PacienteInfoDTO pacienteInfo = new PacienteInfoDTO(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getApellidoPaterno(),
                paciente.getApellidoMaterno(),
                paciente.getDni(),
                paciente.getTelefono(),
                paciente.getEmail(),
                paciente.getDireccion(),
                paciente.getFechaNacimiento() != null ? paciente.getFechaNacimiento().toString() : null,
                paciente.getGenero() != null ? paciente.getGenero().name() : null,
                paciente.getGrupoSanguineo() != null ? paciente.getGrupoSanguineo().name() : null
        );

        List<Cita> citas = citaRepository.findByPacienteIdOrderByFechaHoraDesc(pacienteId);
        List<CitaHistorialDTO> citasDTO = new ArrayList<>();

        for (Cita cita : citas) {
            Diagnostico diagnostico = diagnosticoRepository.findByCitaId(cita.getId()).orElse(null);
            DiagnosticoHistorialDTO diagDTO = null;
            if (diagnostico != null) {
                diagDTO = new DiagnosticoHistorialDTO(
                        diagnostico.getId(),
                        diagnostico.getEnfermedad(),
                        diagnostico.getDescripcion(),
                        diagnostico.getReceta(),
                        diagnostico.getIndicaciones()
                );
            }

            CitaHistorialDTO citaDTO = new CitaHistorialDTO(
                    cita.getId(),
                    cita.getFechaHora(),
                    cita.getMedico().getNombre(),
                    cita.getMedico().getApellidoPaterno(),
                    cita.getMedico().getApellidoMaterno(),
                    cita.getMedico().getEspecialidad().getNombre(),
                    cita.getConsultorio().getNumero(),
                    cita.getMotivoConsulta(),
                    cita.getEstado(),
                    diagDTO
            );
            citasDTO.add(citaDTO);
        }

        return new HistorialMedicoDetalleDTO(pacienteInfo, citasDTO);
    }
}