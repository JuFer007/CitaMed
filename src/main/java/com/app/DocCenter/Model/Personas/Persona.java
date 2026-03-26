package com.app.DocCenter.Model.Personas;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass

public abstract class Persona {
    private String nombre;
    private String apellidoM;
    private String apellidoP;
    private String dni;
    private String telefono;
    private String direccion;
}
