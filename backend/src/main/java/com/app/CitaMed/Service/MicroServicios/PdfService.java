package com.app.CitaMed.Service.MicroServicios;
import com.app.CitaMed.DTO.HistorialPdfDTO;
import com.app.CitaMed.DTO.RecetaDTO;
import com.app.CitaMed.DTO.TicketCitaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Component

public class PdfService {
    private final RestTemplate rest = new RestTemplate();

    @Value("${pdf.service.url:http://localhost:3005}")
    private String pdfServiceUrl;

    public byte[] ticketCita(TicketCitaDTO dto) {
        return rest.postForObject(
                pdfServiceUrl + "/generar-ticket-cita",
                dto,
                byte[].class
        );
    }

    public byte[] receta(RecetaDTO dto) {
        return rest.postForObject(
                pdfServiceUrl + "/generar-receta",
                dto,
                byte[].class
        );
    }

    public byte[] historial(HistorialPdfDTO dto) {
        return rest.postForObject(
                pdfServiceUrl + "/generar-historial",
                dto,
                byte[].class
        );
    }
}
