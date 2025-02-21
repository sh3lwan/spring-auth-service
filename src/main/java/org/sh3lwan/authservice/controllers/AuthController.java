package org.sh3lwan.authservice.controllers;

import org.sh3lwan.authservice.dto.AuthRequest;
import org.sh3lwan.authservice.dto.AuthResponse;
import org.sh3lwan.authservice.dto.RegisterRequest;
import org.sh3lwan.authservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        try {
            String token = authService.registerUser(request);
            AuthResponse response = new AuthResponse(token, "registered");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            AuthResponse response = new AuthResponse("", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        try {
            String token = authService.loginUser(request);
            AuthResponse response = new AuthResponse(token, "logged");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            AuthResponse response = new AuthResponse("", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
