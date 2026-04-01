package com.app.DocCenter.Service.Agenda;
import com.app.DocCenter.Enums.EstadoCita;
import com.app.DocCenter.Model.Agenda.Cita;
import com.app.DocCenter.Repository.Agenda.CitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor

public class CitaService {
    private final CitaRepository citaRepository;

    public List<Cita> findAllCitas() {
        return citaRepository.findAll();
    }

    public Cita findByCita(Long id) {
        return citaRepository.findById(id).orElse(null);
    }

    public String cancelCita(Long id) {
        Cita cita = citaRepository.findById(id).orElse(null);
        if (cita == null) {
            return "Cita no encontrada";
        }
        cita.setEstado(EstadoCita.CANCELADA);
        citaRepository.save(cita);
        return "Cita cancelada correctamente";
    }

    public String completeCita(Long id) {
        Cita cita = citaRepository.findById(id).orElse(null);
        if (cita == null) {
            return "Cita no encontrada";
        }
        cita.setEstado(EstadoCita.ATENDIDA);
        citaRepository.save(cita);
        return "Cita completada correctamente";
    }


    //

    // FALTA AGREGAR CITA

    //



}
