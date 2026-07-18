package com.app.CitaMed.Service.Administrativo;
import com.app.CitaMed.Model.Administrativo.Consulta;
import com.app.CitaMed.Repository.Administrativo.ConsultaRepository;
import com.app.CitaMed.Service.MicroServicios.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final EmailService emailService;

    @Transactional
    public Consulta guardar(String nombre, String email, String mensaje) {
        Consulta consulta = new Consulta();
        consulta.setNombre(nombre);
        consulta.setEmail(email);
        consulta.setMensaje(mensaje);
        return consultaRepository.save(consulta);
    }

    public List<Consulta> listarTodas() {
        return consultaRepository.findAllByOrderByFechaEnvioDesc();
    }

    public long contarNoLeidas() {
        return consultaRepository.countByLeidoFalse();
    }

    public Consulta obtenerYMarcarLeido(Long id) {
        Consulta consulta = consultaRepository.findById(id).orElse(null);
        if (consulta == null) return null;
        if (!consulta.isLeido()) {
            consulta.setLeido(true);
            consultaRepository.save(consulta);
        }
        return consulta;
    }

    @Transactional
    public String responder(Long id, String respuesta, String respondidoPor) {
        Consulta consulta = consultaRepository.findById(id).orElse(null);
        if (consulta == null) return null;

        consulta.setRespuesta(respuesta);
        consulta.setRespondido(true);
        consulta.setFechaRespuesta(LocalDateTime.now());
        consulta.setRespondidoPor(respondidoPor != null ? respondidoPor : "Admin");
        consulta.setLeido(true);
        consultaRepository.save(consulta);

        emailService.enviarRespuestaConsulta(
                consulta.getNombre(),
                consulta.getEmail(),
                consulta.getMensaje(),
                respuesta
        );

        return "Respuesta enviada correctamente";
    }
}