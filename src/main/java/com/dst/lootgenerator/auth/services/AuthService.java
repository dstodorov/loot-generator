package com.dst.lootgenerator.auth.services;

import com.dst.lootgenerator.auth.models.DTO.LoginRequest;
import com.dst.lootgenerator.auth.models.DTO.LoginResponse;
import com.dst.lootgenerator.auth.models.DTO.RegisterRequest;
import com.dst.lootgenerator.auth.models.User;
import com.dst.lootgenerator.core.models.SuccessResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthService {
    User register(RegisterRequest registerRequest, HttpServletRequest request);

    User saveUserData(User user);

    LoginResponse login(LoginRequest loginRequest, HttpServletRequest request);

    LoginResponse googleLogin(String googleToken, HttpServletRequest requestData) throws Exception;

    void forgotPassword(String email, HttpServletRequest request);

    void resetPassword(String token, String newPassword, HttpServletRequest request);

    SuccessResponse refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

    User getLoggedInUser();
}
