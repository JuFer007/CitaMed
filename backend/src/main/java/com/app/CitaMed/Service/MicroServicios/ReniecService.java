package com.app.CitaMed.Service.MicroServicios;
import com.app.CitaMed.DTO.ReniecDataDTO;
import com.app.CitaMed.DTO.ReniecResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;

@Service
@RequiredArgsConstructor
@Component

public class ReniecService {

    @Value("${reniec.api.url}")
    private String apiUrl;

    @Value("${reniec.api.token}")
    private String apiToken;

    private final RestTemplate restTemplate = new RestTemplate();

    public ReniecDataDTO consultarDni(String numero) {

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        String url = UriComponentsBuilder
                .fromUriString(apiUrl)
                .pathSegment(numero)
                .toUriString();

        System.out.println("URL FINAL: " + url);
        System.out.println("DNI: " + numero);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<ReniecResponseDTO> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    ReniecResponseDTO.class
            );

            System.out.println("STATUS: " + response.getStatusCode());
            System.out.println("BODY: " + response.getBody());

            if (response.getBody() != null && response.getBody().isSuccess()) {
                return response.getBody().getData();
            }

            return null;

        } catch (Exception e) {
            System.out.println("ERROR RENIEC:");
            e.printStackTrace();
            return null;
        }
    }
}
