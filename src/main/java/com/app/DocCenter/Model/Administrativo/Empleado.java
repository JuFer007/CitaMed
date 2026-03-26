package com.app.DocCenter.Model.Administrativo;
import com.app.DocCenter.Model.Personas.Persona;
import com.app.DocCenter.Model.Personas.Usuario;
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
@Inheritance(strategy = InheritanceType.JOINED)

public class Empleado extends Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cargo;

    private double salario;

    @OneToOne
    private Usuario usuario;
}
