package com.app.CitaMed.DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor

public class PortalNotificacionDTO {
    private Long id;
    private String mensaje;
    private String tipo;
    private boolean leido;
    private LocalDateTime fechaCreacion;
    private Long referenciaId;
    private String tabDestino;
}