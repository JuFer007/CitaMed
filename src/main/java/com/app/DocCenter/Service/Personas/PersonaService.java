package com.app.DocCenter.Service.Personas;
import com.app.DocCenter.Model.Personas.Persona;
import com.app.DocCenter.Repository.Personas.PersonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor

public class PersonaService {
    private final PersonaRepository personaRepository;

    public String save(Persona persona) {
        boolean existe = personaRepository.existsByDni(persona.getDni());
        if (existe) {
            return "DNI ya registrado, ingrese otro";
        }
        personaRepository.save(persona);
        return "Persona registrada correctamente";
    }

    public List<Persona> findAll() {
        return personaRepository.findAll();
    }

    public Persona findByDNI(String dni) {
        return personaRepository.findByDni(dni);
    }

    public String updatePersona(Persona persona) {
        boolean exists = personaRepository.existsByDni(persona.getDni());
        if (exists) {
            Persona personaExistente = personaRepository.findByDni(persona.getDni());
            personaExistente.setNombre(persona.getNombre());
            personaExistente.setApellidoM(persona.getApellidoM());
            personaExistente.setApellidoP(persona.getApellidoP());
            personaExistente.setDireccion(persona.getDireccion());
            personaExistente.setTelefono(persona.getTelefono());
            personaRepository.save(personaExistente);
            return "Datos modificados correctamente";
        }
        return "DNI ingresado no esta registrado";
    }
}
