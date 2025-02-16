package com.dst.lootgenerator.auth.services;

import com.dst.lootgenerator.auth.models.DTO.LoginRequest;
import com.dst.lootgenerator.auth.models.DTO.LoginResponse;
import com.dst.lootgenerator.auth.models.DTO.RegisterRequest;
import com.dst.lootgenerator.auth.models.Role;
import com.dst.lootgenerator.auth.models.User;
import com.dst.lootgenerator.auth.repositories.UserRepository;
import com.dst.lootgenerator.core.security.JwtService;
import com.dst.lootgenerator.logger.models.ActionType;
import com.dst.lootgenerator.logger.models.LogData;
import com.dst.lootgenerator.logger.services.LoggerService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService, EmailService emailService, UserDetailsService userDetailsService, LoggerService loggerService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.emailService = emailService;
        this.userDetailsService = userDetailsService;
        this.loggerService = loggerService;
    }

    @Override
    public User register(RegisterRequest registerRequest) {
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRoles(Set.of(Role.USER)); // По подразбиране, новорегистрираните потребители са USER-и
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

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String accessToken = jwtService.generateToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);

        Enumeration<String> headerNames = requestData.getHeaderNames();
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

    @Override
    public LoginResponse refreshToken(String refreshToken) {
        if (refreshToken == null || refreshToken.isEmpty()) {
            throw new IllegalArgumentException("Refresh token is required");
        }

        if (!jwtService.isTokenValid(refreshToken)) { //Използваме isTokenValid, като подаваме null за UserDetails, защото вече сме го извлекли.
            throw new JwtException("Invalid refresh token");
        }

        String username = jwtService.extractUsername(refreshToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (userDetails == null) {
            throw new UsernameNotFoundException("User not found");
        }

        String accessToken = jwtService.generateToken(userDetails);
        String newRefreshToken = jwtService.generateRefreshToken(userDetails);

        return new LoginResponse(accessToken, newRefreshToken);
    }
}
