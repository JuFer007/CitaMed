package com.app.CitaMed.DTO;
import com.app.CitaMed.Enums.EstadoCita;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class HistorialMedicoDetalleDTO {
    private PacienteInfoDTO paciente;
    private List<CitaHistorialDTO> citas;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor

    public static class PacienteInfoDTO {
        private Long id;
        private String nombre;
        private String apellidoPaterno;
        private String apellidoMaterno;
        private String dni;
        private String telefono;
        private String email;
        private String direccion;
        private String fechaNacimiento;
        private String genero;
        private String grupoSanguineo;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor

    public static class CitaHistorialDTO {
        private Long citaId;
        private LocalDateTime fechaHora;
        private String medicoNombre;
        private String medicoApellidoPaterno;
        private String medicoApellidoMaterno;
        private String medicoEspecialidad;
        private String consultorioNumero;
        private String motivoConsulta;
        private EstadoCita estado;
        private DiagnosticoHistorialDTO diagnostico;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor

    public static class DiagnosticoHistorialDTO {
        private Long id;
        private String enfermedad;
        private String descripcion;
        private String receta;
        private String indicaciones;
    }
}
