package com.app.CitaMed.Service.MicroServicios;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class MailerSendService {

    @Value("${mailersend.api.token}")
    private String apiToken;

    @Value("${mailersend.from.email}")
    private String fromEmail;

    @Value("${mailersend.from.name}")
    private String fromName;

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String API_URL = "https://api.mailersend.com/v1/email";

    public boolean enviarEmail(String toEmail, String toNombre, String asunto, String mensajeHtml, String mensajeTexto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiToken);

        Map<String, Object> body = new HashMap<>();
        body.put("from", Map.of("email", fromEmail, "name", fromName));
        body.put("to", List.of(Map.of("email", toEmail, "name", toNombre)));
        body.put("subject", asunto);
        body.put("html", mensajeHtml);
        body.put("text", mensajeTexto);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, String.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            System.err.println("Error enviando email: " + e.getMessage());
            e.printStackTrace(); // Mejor logging en desarrollo
            return false;
        }
    }
}
