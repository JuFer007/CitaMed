package com.app.CitaMed.Service.Portal;
import com.app.CitaMed.DTO.PortalNotificacionDTO;
import com.app.CitaMed.Model.Paciente.Paciente;
import com.app.CitaMed.Model.Portal.Notificacion;
import com.app.CitaMed.Repository.Portal.NotificacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PortalNotificacionService {

    private final NotificacionRepository notificacionRepository;

    public List<PortalNotificacionDTO> obtenerNotificaciones(Long pacienteId) {
        return notificacionRepository.findByPacienteIdOrderByFechaCreacionDesc(pacienteId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public long contarNoLeidas(Long pacienteId) {
        return notificacionRepository.countByPacienteIdAndLeidoFalse(pacienteId);
    }

    @Transactional
    public void marcarComoLeida(Long notificacionId, Long pacienteId) {
        Notificacion n = notificacionRepository.findById(notificacionId)
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada"));
        if (!n.getPaciente().getId().equals(pacienteId)) {
            throw new RuntimeException("La notificación no pertenece al paciente");
        }
        n.setLeido(true);
        notificacionRepository.save(n);
    }

    @Transactional
    public void marcarTodasComoLeidas(Long pacienteId) {
        List<Notificacion> noLeidas = notificacionRepository
                .findByPacienteIdAndLeidoFalseOrderByFechaCreacionDesc(pacienteId);
        noLeidas.forEach(n -> n.setLeido(true));
        notificacionRepository.saveAll(noLeidas);
    }

    @Transactional
    public void crearNotificacion(Paciente paciente, String mensaje, String tipo,
                                   Long referenciaId, String tabDestino) {
        Notificacion n = new Notificacion();
        n.setPaciente(paciente);
        n.setMensaje(mensaje);
        n.setTipo(tipo);
        n.setLeido(false);
        n.setFechaCreacion(LocalDateTime.now());
        n.setReferenciaId(referenciaId);
        n.setTabDestino(tabDestino);
        notificacionRepository.save(n);
    }

    private PortalNotificacionDTO toDTO(Notificacion n) {
        return PortalNotificacionDTO.builder()
                .id(n.getId())
                .mensaje(n.getMensaje())
                .tipo(n.getTipo())
                .leido(n.getLeido())
                .fechaCreacion(n.getFechaCreacion())
                .referenciaId(n.getReferenciaId())
                .tabDestino(n.getTabDestino())
                .build();
    }
}
