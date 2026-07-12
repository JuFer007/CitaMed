package com.app.CitaMed.Service.Portal;
import com.app.CitaMed.DTO.PortalTestimonioDTO;
import com.app.CitaMed.DTO.TestimonioDTO;
import com.app.CitaMed.DTO.TestimonioPublicoDTO;
import com.app.CitaMed.Enums.EstadoCita;
import com.app.CitaMed.Model.Agenda.Cita;
import com.app.CitaMed.Model.Paciente.Paciente;
import com.app.CitaMed.Model.Paciente.Testimonio;
import com.app.CitaMed.Repository.Agenda.CitaRepository;
import com.app.CitaMed.Repository.Paciente.TestimonioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PortalTestimonioService {

    private final TestimonioRepository testimonioRepository;
    private final CitaRepository citaRepository;

    @Transactional
    public PortalTestimonioDTO crear(Long pacienteId, TestimonioDTO dto, Paciente paciente) {
        List<Cita> citasAtendidas = citaRepository.findByPacienteId(pacienteId);
        boolean tieneAtendida = citasAtendidas.stream()
                .anyMatch(c -> c.getEstado() == EstadoCita.ATENDIDA);

        if (!tieneAtendida) {
            throw new RuntimeException("Debes tener al menos una consulta atendida para dejar una reseña");
        }

        Testimonio testimonio = new Testimonio();
        testimonio.setCalificacion(dto.getCalificacion());
        testimonio.setMensaje(dto.getMensaje());
        testimonio.setFechaCreacion(LocalDateTime.now());
        testimonio.setActivo(true);
        testimonio.setPaciente(paciente);
        testimonioRepository.save(testimonio);

        return new PortalTestimonioDTO(
                testimonio.getId(),
                testimonio.getCalificacion(),
                testimonio.getMensaje(),
                testimonio.getFechaCreacion()
        );
    }

    public List<PortalTestimonioDTO> obtenerMisResenas(Long pacienteId) {
        return testimonioRepository.findByPacienteIdOrderByFechaCreacionDesc(pacienteId)
                .stream()
                .filter(Testimonio::getActivo)
                .map(t -> new PortalTestimonioDTO(t.getId(), t.getCalificacion(), t.getMensaje(), t.getFechaCreacion()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void eliminar(Long id, Long pacienteId) {
        Testimonio testimonio = testimonioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reseña no encontrada"));

        if (!testimonio.getPaciente().getId().equals(pacienteId)) {
            throw new RuntimeException("Esta reseña no pertenece al paciente");
        }

        testimonio.setActivo(false);
        testimonioRepository.save(testimonio);
    }

    public boolean tieneAtendida(Long pacienteId) {
        List<Cita> citas = citaRepository.findByPacienteId(pacienteId);
        return citas.stream().anyMatch(c -> c.getEstado() == EstadoCita.ATENDIDA);
    }

    public List<TestimonioPublicoDTO> obtenerTestimoniosPublicos() {
        return testimonioRepository.findByActivoTrueOrderByFechaCreacionDesc()
                .stream()
                .map(t -> {
                    String nombre = t.getPaciente().getNombre();
                    String apellido = t.getPaciente().getApellidoPaterno();
                    String iniciales = nombre.charAt(0) + ". " + apellido;
                    return new TestimonioPublicoDTO(iniciales, t.getCalificacion(), t.getMensaje(), t.getFechaCreacion());
                })
                .collect(Collectors.toList());
    }
}
