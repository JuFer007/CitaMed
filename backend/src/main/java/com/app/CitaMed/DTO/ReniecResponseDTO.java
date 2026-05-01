package com.app.CitaMed.DTO;
import lombok.Data;

@Data

public class ReniecResponseDTO {
    private boolean success;
    private ReniecDataDTO data;
    private String message;
}
