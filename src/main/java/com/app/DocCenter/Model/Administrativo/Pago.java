package com.app.DocCenter.Model.Administrativo;
import com.app.DocCenter.Enums.EstadoPago;
import com.app.DocCenter.Enums.MetodoPago;
import com.app.DocCenter.Model.Agenda.Cita;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "cita_id", nullable = false)
    private Cita cita;

    private double monto;

    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;

    @Enumerated(EnumType.STRING)
    private EstadoPago estado;

    private LocalDateTime fechaPago;
    private String comprobante;
}
