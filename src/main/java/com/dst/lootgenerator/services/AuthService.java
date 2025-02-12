package com.dst.lootgenerator.services;

import com.dst.lootgenerator.models.DTO.LoginRequest;
import com.dst.lootgenerator.models.DTO.LoginResponse;
import com.dst.lootgenerator.models.DTO.RegisterRequest;
import com.dst.lootgenerator.models.User;

public interface AuthService {
    User register(RegisterRequest registerRequest);
    LoginResponse login(LoginRequest loginRequest);
    void forgotPassword(String email);
    void resetPassword(String token, String newPassword);
    LoginResponse refreshToken(String refreshToken);
}
