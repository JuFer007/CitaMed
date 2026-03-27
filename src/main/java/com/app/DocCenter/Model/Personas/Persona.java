package com.app.DocCenter.Model.Personas;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor

public abstract class Persona {
    private String nombre;
    private String apellidoM;
    private String apellidoP;
    private String dni;
    private String telefono;
    private String direccion;
    private String email;
}
