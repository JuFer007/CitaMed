package com.app.CitaMed.DTO;

import com.app.CitaMed.Enums.Rol;
import lombok.Data;

@Data
public class UsuarioUpdateDTO {
    private String userName;
    private String password;
    private Rol rol;
}
