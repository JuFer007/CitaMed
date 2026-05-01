package com.app.CitaMed.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmailRequestDTO {
    private String destino;
    private String asunto;
    private String html;
}
