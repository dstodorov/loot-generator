package com.dst.lootgenerator.auth.controllers;

import com.dst.lootgenerator.auth.models.DTO.*;
import com.dst.lootgenerator.auth.models.User;
import com.dst.lootgenerator.auth.services.AuthService;
import com.dst.lootgenerator.core.models.SuccessResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
    public ResponseEntity<SuccessResponse> register(@RequestBody @Valid RegisterRequest registerRequest, UriComponentsBuilder uri) {
        User registeredUser = authService.register(registerRequest);

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
                HttpStatus.ACCEPTED,
                loginResponse
        );

        return ResponseEntity.ok(successResponse);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        try {
            authService.forgotPassword(forgotPasswordRequest.getEmail());
            return ResponseEntity.ok().build(); // Връщаме 200 OK при успешен request.
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); //Връщаме 404 Not Found ако потребителя не е намерен.
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //Връщаме 500 Internal Server Error при други грешки.
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        try {
            authService.resetPassword(resetPasswordRequest.getToken(), resetPasswordRequest.getNewPassword());
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            if (e.getMessage().equals("Invalid or expired token")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired token"); //400
            } else if (e.getMessage().equals("Token expired")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token expired");//400
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
        }
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authService.refreshToken(request, response);
    }

    @GetMapping("/dummySecure")
    public ResponseEntity<?> dummySecure() {
        return ResponseEntity.ok().build();
    }
}
