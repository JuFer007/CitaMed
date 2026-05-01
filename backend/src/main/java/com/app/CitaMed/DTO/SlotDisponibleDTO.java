package com.app.CitaMed.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor

public class SlotDisponibleDTO {
    private Long medicoId;
    private String medicoNombre;
    private String medicoApellidoPaterno;
    private String medicoGenero;
    private String especialidadNombre;
    private Long consultorioId;
    private List<String> horasDisponibles;
}
