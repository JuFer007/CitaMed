package com.app.CitaMed.Model.Administrativo;
import com.app.CitaMed.Enums.EstadoPago;
import com.app.CitaMed.Enums.MetodoPago;
import com.app.CitaMed.Model.Agenda.Cita;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
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
@Table(name = "Pagos")

public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La cita es obligatoria")
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "cita_id", nullable = false, unique = true)
    private Cita cita;

    @Positive(message = "El monto debe ser mayor que 0")
    private double monto;

    @NotNull(message = "Debe seleccionar un método de pago")
    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;

    @NotNull(message = "Debe indicar el estado del pago")
    @Enumerated(EnumType.STRING)
    private EstadoPago estado;

    @PastOrPresent(message = "La fecha de pago no puede ser futura")
    private LocalDateTime fechaPago;
}
