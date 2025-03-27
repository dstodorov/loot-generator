package com.dst.lootgenerator.auth.controllers;

import com.dst.lootgenerator.auth.models.DTO.*;
import com.dst.lootgenerator.auth.models.User;
import com.dst.lootgenerator.auth.services.AuthService;
import com.dst.lootgenerator.core.models.SuccessResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.time.Instant;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<SuccessResponse> register(@RequestBody @Valid RegisterRequest registerRequest, UriComponentsBuilder uri, HttpServletRequest request) {
        User registeredUser = authService.register(registerRequest, request);

        RegisterResponse registerResponse = new RegisterResponse(
                registeredUser.getId(),
                registeredUser.getEmail(),
                registeredUser.getRoles()
        );

        SuccessResponse successResponse = new SuccessResponse(
                Instant.now(),
                HttpStatus.CREATED,
                registerResponse
        );

        UriComponents uriComponents = uri.path("/users/{id}").buildAndExpand(registeredUser.getId());

        return ResponseEntity.created(uriComponents.toUri()).body(successResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<SuccessResponse> login(@RequestBody @Valid LoginRequest loginRequest, HttpServletRequest requestData) {
        LoginResponse loginResponse = authService.login(loginRequest, requestData);

        SuccessResponse successResponse = new SuccessResponse(
                Instant.now(),
                HttpStatus.OK,
                loginResponse
        );

        return ResponseEntity.ok(successResponse);
    }

    // NOT TESTED, NEED TO BE REFACTORED
    @PostMapping("/google-login")
    public ResponseEntity<SuccessResponse> googleLogin(@RequestBody String googleToken, HttpServletRequest requestData) {
        try {
            LoginResponse loginResponse = authService.googleLogin(googleToken, requestData);

            SuccessResponse successResponse = new SuccessResponse(
                    Instant.now(),
                    HttpStatus.OK,
                    loginResponse
            );

            return ResponseEntity.ok(successResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest, HttpServletRequest request) {
        authService.forgotPassword(forgotPasswordRequest.getEmail(), request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh-token")
    public SuccessResponse refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        return authService.refreshToken(request, response);
    }

    @GetMapping("/dummySecure")
    public ResponseEntity<?> dummySecure() {
        return ResponseEntity.ok().build();
    }
}
