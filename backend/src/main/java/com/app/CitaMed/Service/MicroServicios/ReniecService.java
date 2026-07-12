package com.app.CitaMed.Service.MicroServicios;
import com.app.CitaMed.DTO.ReniecDataDTO;
import com.app.CitaMed.DTO.ReniecResponseDTO;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Component

public class ReniecService {

    @Value("${reniec.api.url}")
    private String apiUrl;

    @Value("${reniec.api.token}")
    private String apiToken;

    private RestTemplate restTemplate;

    private final Map<String, ReniecDataDTO> cacheDni = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        var factory = new org.springframework.http.client.SimpleClientHttpRequestFactory();
        factory.setConnectTimeout((int) Duration.ofSeconds(5).toMillis());
        factory.setReadTimeout((int) Duration.ofSeconds(10).toMillis());
        this.restTemplate = new RestTemplate(factory);
    }

    public ReniecDataDTO consultarDni(String numero) {
        ReniecDataDTO cached = cacheDni.get(numero);
        if (cached != null) {
            System.out.println("RENIEC CACHE HIT: " + numero);
            return cached;
        }

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
                cacheDni.put(numero, response.getBody().getData());
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