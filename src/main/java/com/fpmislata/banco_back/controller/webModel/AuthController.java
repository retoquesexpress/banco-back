package com.fpmislata.banco_back.controller.webModel;


import com.fpmislata.banco_back.controller.webModel.request.AuthRequest;
import com.fpmislata.banco_back.controller.webModel.response.AuthResponse;
import com.fpmislata.banco_back.domain.service.AuthService;
import com.fpmislata.banco_back.domain.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final ClientService clientService;

    public AuthController(AuthService authService, ClientService clientService) {
        this.authService = authService;
        this.clientService = clientService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            AuthResponse authResponse = authService.login(request);
            return ResponseEntity.ok(authResponse);
        } catch (RuntimeException e){
            String message = e.getMessage();
            if (message.contains("User not found")){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
            } else if (message.contains("Invalid credentials")){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al Iniciar Sesión");
            }
        }
    }
}