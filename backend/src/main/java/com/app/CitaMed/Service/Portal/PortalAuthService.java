package com.app.CitaMed.Service.Portal;
import com.app.CitaMed.DTO.PerfilRequest;
import com.app.CitaMed.DTO.PortalPerfilDTO;
import com.app.CitaMed.DTO.RegisterRequest;
import com.app.CitaMed.Enums.Genero;
import com.app.CitaMed.Enums.GrupoSanguineo;
import com.app.CitaMed.Enums.Rol;
import com.app.CitaMed.Model.Administrativo.Usuario;
import com.app.CitaMed.Model.Paciente.HistorialMedico;
import com.app.CitaMed.Model.Paciente.Paciente;
import com.app.CitaMed.Repository.Administrativo.UsuarioRepository;
import com.app.CitaMed.Repository.Paciente.HistorialMedicoRepository;
import com.app.CitaMed.Repository.Paciente.PacienteRepository;
import com.app.CitaMed.Service.Administrativo.UsuarioService;
import com.app.CitaMed.Service.MicroServicios.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PortalAuthService {

    private final PacienteRepository pacienteRepository;
    private final UsuarioRepository usuarioRepository;
    private final HistorialMedicoRepository historialMedicoRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Transactional
    public void registrar(RegisterRequest request) {
        if (usuarioRepository.existsByUserName(request.getEmail())) {
            throw new RuntimeException("El email ya está registrado como usuario");
        }

        String userName = request.getEmail().split("@")[0];
        String baseUserName = userName;
        int suffix = 1;
        while (usuarioRepository.existsByUserName(userName)) {
            userName = baseUserName + suffix;
            suffix++;
        }

        Usuario usuario = new Usuario();
        usuario.setUserName(userName);
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRol(Rol.PACIENTE);
        usuario.setActivo(true);
        usuario = usuarioRepository.save(usuario);

        Paciente paciente = pacienteRepository.findByDni(request.getDni());
        if (paciente != null) {
            if (paciente.getUsuario() != null) {
                throw new RuntimeException("Este paciente ya tiene una cuenta activa");
            }
            paciente.setTelefono(request.getTelefono());
            paciente.setEmail(request.getEmail());
            paciente.setDireccion(request.getDireccion());
            paciente.setUsuario(usuario);
            pacienteRepository.save(paciente);
        } else {
            paciente = new Paciente();
            paciente.setNombre(request.getNombre().toUpperCase());
            paciente.setApellidoPaterno(request.getApellidoPaterno().toUpperCase());
            paciente.setApellidoMaterno(request.getApellidoMaterno().toUpperCase());
            paciente.setDni(request.getDni());
            paciente.setTelefono(request.getTelefono());
            paciente.setEmail(request.getEmail());
            paciente.setDireccion(request.getDireccion());
            paciente.setFechaNacimiento(request.getFechaNacimiento());
            paciente.setGenero(Genero.valueOf(request.getGenero()));
            paciente.setGrupoSanguineo(GrupoSanguineo.O_POSITIVO);
            paciente.setUsuario(usuario);
            paciente.setActivo(true);
            paciente = pacienteRepository.save(paciente);

            HistorialMedico historial = new HistorialMedico();
            historial.setPaciente(paciente);
            historialMedicoRepository.save(historial);
        }

        String nombreCompleto = paciente.getNombre() + " " + paciente.getApellidoPaterno();
        emailService.enviarRegistro(nombreCompleto, paciente.getEmail(), paciente.getEmail());
    }

    public PortalPerfilDTO obtenerPerfil(Long pacienteId) {
        Paciente p = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        return PortalPerfilDTO.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .apellidoPaterno(p.getApellidoPaterno())
                .apellidoMaterno(p.getApellidoMaterno())
                .dni(p.getDni())
                .telefono(p.getTelefono())
                .direccion(p.getDireccion())
                .email(p.getEmail())
                .fechaNacimiento(p.getFechaNacimiento())
                .genero(p.getGenero().name())
                .grupoSanguineo(p.getGrupoSanguineo().name())
                .build();
    }

    @Transactional
    public void actualizarPerfil(Long pacienteId, PerfilRequest request) {
        Paciente p = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        if (!p.getEmail().equals(request.getEmail())
                && pacienteRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está en uso por otro paciente");
        }

        p.setTelefono(request.getTelefono());
        p.setDireccion(request.getDireccion());
        p.setEmail(request.getEmail());
        pacienteRepository.save(p);
    }

    public void solicitarRecuperarPassword(String email) {
        Paciente paciente = pacienteRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("No existe una cuenta registrada con este email"));

        if (paciente.getUsuario() == null) {
            throw new RuntimeException("No se encontró la cuenta asociada");
        }

        Usuario usuario = paciente.getUsuario();
        String codigo = usuarioService.generarCodigoRecuperacion(usuario);
        String nombre = paciente.getNombre() + " " + paciente.getApellidoPaterno();
        emailService.enviarCodigoRecuperacion(nombre, email, codigo);
    }

    public void restablecerPassword(String token, String nuevaPassword) {
        usuarioService.restablecerContrasena(token, nuevaPassword);
    }
}
