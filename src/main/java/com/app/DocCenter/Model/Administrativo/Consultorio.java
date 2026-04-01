package com.app.DocCenter.Model.Administrativo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Consultorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private String descripcion;
    private boolean disponible;

    @ManyToOne
    @JoinColumn(name = "area_id", nullable = false)
    private Area area;
}
