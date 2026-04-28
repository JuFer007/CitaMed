package com.app.CitaMed.Response;
import com.app.CitaMed.DTO.ReniecDataDTO;
import lombok.Data;

@Data

public class ReniecResponse {
    private boolean success;
    private ReniecDataDTO data;
    private String message;
}
