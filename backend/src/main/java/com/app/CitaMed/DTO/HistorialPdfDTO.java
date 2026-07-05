package com.app.CitaMed.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class HistorialPdfDTO {
    private String paciente;
    private String dni;
    private String edad;
    private String genero;
    private String grupoSanguineo;
    private String telefono;
    private String email;
    private String totalCitas;
    private List<CitaPdfDTO> citas;
    private List<DiagnosticoPdfDTO> diagnosticos;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CitaPdfDTO {
        private String fecha;
        private String medico;
        private String especialidad;
        private String motivo;
        private String estado;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DiagnosticoPdfDTO {
        private String enfermedad;
        private String descripcion;
        private String receta;
        private String indicaciones;
    }
}
