package com.dst.lootgenerator.auth.services;

import com.dst.lootgenerator.auth.models.DTO.LoginRequest;
import com.dst.lootgenerator.auth.models.DTO.LoginResponse;
import com.dst.lootgenerator.auth.models.DTO.RegisterRequest;
import com.dst.lootgenerator.auth.models.Role;
import com.dst.lootgenerator.auth.models.Token;
import com.dst.lootgenerator.auth.models.TokenType;
import com.dst.lootgenerator.auth.models.User;
import com.dst.lootgenerator.auth.repositories.TokenRepository;
import com.dst.lootgenerator.auth.repositories.UserRepository;
import com.dst.lootgenerator.core.security.JwtService;
import com.dst.lootgenerator.logger.models.ActionType;
import com.dst.lootgenerator.logger.models.LogData;
import com.dst.lootgenerator.logger.services.LoggerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Enumeration;
import java.util.Set;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;
    private final EmailService emailService;
    private final UserDetailsService userDetailsService;
    private final LoggerService loggerService;
    private final TokenRepository tokenRepository;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService, EmailService emailService, UserDetailsService userDetailsService, LoggerService loggerService, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.emailService = emailService;
        this.userDetailsService = userDetailsService;
        this.loggerService = loggerService;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public User register(RegisterRequest registerRequest) {
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRoles(Set.of(Role.USER));
        return userRepository.save(user);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest, HttpServletRequest requestData) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        User user = userRepository
                .findByEmail(loginRequest.getEmail())
                .orElseThrow();

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String accessToken = jwtService.generateToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);

        LogData logData = LogData
                .builder()
                .user(userDetails.getUsername())
                .action(ActionType.LOGIN)
                .ipAddress(requestData.getRemoteAddr())
                .deviceType(requestData.getHeader("User-Agent"))
                .build();

        loggerService.log(logData);

        return new LoginResponse(accessToken, refreshToken);
    }

    public void forgotPassword(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String token = UUID.randomUUID().toString();
        user.setResetPasswordToken(token);
        user.setResetPasswordTokenExpiryDate(Date.from(Instant.now().plus(24, ChronoUnit.HOURS))); // 24 hours validity
        userRepository.save(user);

        emailService.sendPasswordResetEmail(email, "Password Reset Request", token);
    }

    public void resetPassword(String token, String newPassword) {
        User user = userRepository.findByResetPasswordToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid or expired token"));

        if (user.getResetPasswordTokenExpiryDate().before(new Date())) {
            throw new RuntimeException("Token expired");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetPasswordToken(null);
        user.setResetPasswordTokenExpiryDate(null);
        userRepository.save(user);
    }

    private void saveUserToken(User user, String jwtToken) {
        Token token = new Token(0, jwtToken, TokenType.BEARER, false, false, user);
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty()) {
            return;
        }

        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });

        tokenRepository.saveAll(validUserTokens);
    }

    @Override
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String username;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            //throw new RefreshTokenFailureException(ApplicationMessages.REFRESH_TOKEN_FAILURE);
        }
        refreshToken = authHeader.substring(7);
        username = jwtService.extractUsername(refreshToken);
        if (username != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            User user = this.userRepository.findByEmail(username)
                    .orElseThrow();

            if (jwtService.isTokenValid(refreshToken, userDetails)) {
                String accessToken = jwtService.generateToken(userDetails);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                LoginResponse loginResponse = new LoginResponse(accessToken, refreshToken);

                new ObjectMapper().writeValue(response.getOutputStream(), loginResponse);
            }
        }
    }
}
