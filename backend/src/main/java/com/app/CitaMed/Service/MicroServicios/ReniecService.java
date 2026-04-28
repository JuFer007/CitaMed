package com.app.CitaMed.Service.MicroServicios;
import com.app.CitaMed.DTO.ReniecDataDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ReniecService {

    @Value("${reniec.api.url}")
    private String apiUrl;

    @Value("${reniec.api.token}")
    private String apiToken;

    private final RestTemplate restTemplate = new RestTemplate();

    public ReniecDataDTO consultarDni(String numero) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiToken);

        String url = UriComponentsBuilder.fromUriString(apiUrl)
                .queryParam("numero", numero)
                .build()
                .toUriString();

        // 🔥 DEBUG
        System.out.println("TOKEN: " + apiToken);
        System.out.println("URL: " + url);
        System.out.println("DNI: " + numero);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<ReniecDataDTO> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    ReniecDataDTO.class
            );

            System.out.println("STATUS: " + response.getStatusCode());
            System.out.println("BODY: " + response.getBody());

            return response.getBody();

        } catch (Exception e) {

            System.out.println("ERROR COMPLETO:");
            e.printStackTrace();

            return null;
        }
    }
}
