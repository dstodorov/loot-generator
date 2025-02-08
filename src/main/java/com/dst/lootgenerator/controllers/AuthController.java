package com.dst.lootgenerator.controllers;

import com.dst.lootgenerator.models.DTO.*;
import com.dst.lootgenerator.models.User;
import com.dst.lootgenerator.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterRequest registerRequest) {
        User registeredUser = authService.register(registerRequest);

        RegisterResponse registerResponse = new RegisterResponse(
                registeredUser.getId(),
                registeredUser.getEmail(),
                registeredUser.getRoles()
        );

        return ResponseEntity.ok(registerResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        LoginResponse loginResponse = authService.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }

//    @PostMapping("/forgot-password")
//    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
//        authService.forgotPassword(forgotPasswordRequest.getEmail());
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/reset-password")
//    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
//        authService.resetPassword(resetPasswordRequest.getToken(), resetPasswordRequest.getNewPassword());
//        return ResponseEntity.ok().build();
//    }
}
