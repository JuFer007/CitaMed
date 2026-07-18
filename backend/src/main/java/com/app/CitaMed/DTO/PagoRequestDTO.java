package com.app.CitaMed.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PagoRequestDTO {
    @NotNull
    private Long citaId;

    @NotBlank
    private String paymentIntentId;
}
