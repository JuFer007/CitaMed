package com.app.CitaMed.Controller.Util;
import com.app.CitaMed.Service.Administrativo.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor

public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> credentials){
        return authService.login(
                credentials.get("usuario"),
                credentials.get("clave")
        );
    }
}
