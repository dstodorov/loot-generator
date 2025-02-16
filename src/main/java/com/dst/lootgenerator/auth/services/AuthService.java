package com.dst.lootgenerator.auth.services;

import com.dst.lootgenerator.auth.models.DTO.LoginRequest;
import com.dst.lootgenerator.auth.models.DTO.LoginResponse;
import com.dst.lootgenerator.auth.models.DTO.RegisterRequest;
import com.dst.lootgenerator.auth.models.User;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
    User register(RegisterRequest registerRequest);
    LoginResponse login(LoginRequest loginRequest, HttpServletRequest request);
    void forgotPassword(String email);
    void resetPassword(String token, String newPassword);
    LoginResponse refreshToken(String refreshToken);
}
