package com.app.DocCenter.Service.Administrativo;
import com.app.DocCenter.DTO.PagoDTO;
import com.app.DocCenter.Enums.EstadoCita;
import com.app.DocCenter.Enums.EstadoPago;
import com.app.DocCenter.Model.Administrativo.Pago;
import com.app.DocCenter.Model.Agenda.Cita;
import com.app.DocCenter.Repository.Administrativo.PagoRepository;
import com.app.DocCenter.Repository.Agenda.CitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class PagoService {
    private final PagoRepository pagoRepository;
    private final CitaRepository citaRepository;

    public List<Pago> findAll() {
        return pagoRepository.findAll();
    }

    public Pago findById(Long id) {
        return pagoRepository.findById(id).orElse(null);
    }

    public String save(PagoDTO dto) {
        Cita cita = citaRepository.findById(dto.getCitaId()).orElse(null);
        if (cita == null) return "Cita no encontrada";

        if (pagoRepository.existsByCitaId(dto.getCitaId()))
            return "Esta cita ya tiene un pago registrado";

        if (cita.getEstado() == EstadoCita.CANCELADA)
            return "No se puede registrar pago de una cita cancelada";

        Pago pago = new Pago();
        pago.setCita(cita);
        pago.setMonto(dto.getMonto());
        pago.setMetodoPago(dto.getMetodoPago());
        pago.setEstado(EstadoPago.PAGADO);
        pago.setFechaPago(LocalDateTime.now());
        pagoRepository.save(pago);
        return "Pago registrado correctamente";
    }

    public String anular(Long id) {
        Pago pago = pagoRepository.findById(id).orElse(null);
        if (pago == null) return "Pago no encontrado";
        pago.setEstado(EstadoPago.ANULADO);
        pagoRepository.save(pago);
        return "Pago anulado correctamente";
    }
}
