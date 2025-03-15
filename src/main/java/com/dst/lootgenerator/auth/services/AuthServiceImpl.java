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
import com.dst.lootgenerator.core.exceptions.models.ExpiredTokenException;
import com.dst.lootgenerator.core.exceptions.models.InvalidTokenException;
import com.dst.lootgenerator.core.exceptions.models.UsernameNotFoundException;
import com.dst.lootgenerator.core.security.JwtService;
import com.dst.lootgenerator.logger.models.ActionType;
import com.dst.lootgenerator.logger.models.UserActionData;
import com.dst.lootgenerator.logger.models.UserActionLog;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;
    private final EmailServiceImpl emailServiceImpl;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Value("${google.client-id}")
    private String googleClientId;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService, EmailServiceImpl emailServiceImpl, UserDetailsService userDetailsService, TokenRepository tokenRepository, ApplicationEventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.emailServiceImpl = emailServiceImpl;
        this.userDetailsService = userDetailsService;
        this.tokenRepository = tokenRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public User register(RegisterRequest registerRequest, HttpServletRequest requestData) {
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRoles(Set.of(Role.USER));

        // Publish async Register event to write into the database
        publishEvent(ActionType.REGISTER, registerRequest.getEmail(), requestData);

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
                .orElseThrow(() -> new UsernameNotFoundException("User not found", HttpStatus.NOT_FOUND, loginRequest.getEmail()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String accessToken = jwtService.generateToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);

        // Publish async Login event to write into the database
        publishEvent(ActionType.LOGIN, loginRequest.getEmail(), requestData);

        return new LoginResponse(accessToken, refreshToken);
    }

    @Override
    public LoginResponse googleLogin(String googleToken, HttpServletRequest requestData) throws Exception {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singletonList(googleClientId))
                .build();

        GoogleIdToken idToken = verifier.verify(googleToken);
        if (idToken == null) {
            throw new Exception("Invalid Google token");
        }

        GoogleIdToken.Payload payload = idToken.getPayload();
        String email = payload.getEmail();
        String name = (String) payload.get("name");


        // Must change that logic
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            user = new User();
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
            user.setRoles(Set.of(Role.USER));
            userRepository.save(user);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        String accessToken = jwtService.generateToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);

        // Publish async Login event to write into the database
        publishEvent(ActionType.LOGIN, user.getEmail(), requestData);

        return new LoginResponse(accessToken, refreshToken);
    }

    @Override
    public void forgotPassword(String email, HttpServletRequest requestData) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found", HttpStatus.NOT_FOUND, email));

        String token = UUID.randomUUID().toString();
        user.setResetPasswordToken(token);
        user.setResetPasswordTokenExpiryDate(Date.from(Instant.now().plus(24, ChronoUnit.HOURS))); // 24 hours validity
        userRepository.save(user);

        // Publish async Forgot Password event to write into the database
        publishEvent(ActionType.FORGOT_PASSWORD, user.getEmail(), requestData);

        emailServiceImpl.sendPasswordResetEmail(email, "Password Reset Request", token);

    }

    @Override
    public void resetPassword(String token, String newPassword, HttpServletRequest requestData) {
        User user = userRepository.findByResetPasswordToken(token)
                .orElseThrow(() -> new InvalidTokenException("Invalid token", HttpStatus.UNAUTHORIZED));

        if (user.getResetPasswordTokenExpiryDate().before(new Date())) {
            throw new ExpiredTokenException("Token expired", HttpStatus.UNAUTHORIZED);
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetPasswordToken(null);
        user.setResetPasswordTokenExpiryDate(null);
        userRepository.save(user);

        // Publish async Reset Password event to write into the database
        publishEvent(ActionType.RESET_PASSWORD, user.getEmail(), requestData);
    }

    @Override
    public void refreshToken(
            HttpServletRequest requestData,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = requestData.getHeader(HttpHeaders.AUTHORIZATION);
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

                // Publish async Refresh Token event to write into the database
                publishEvent(ActionType.TOKEN_REFRESH, user.getEmail(), requestData);

                new ObjectMapper().writeValue(response.getOutputStream(), loginResponse);
            }
        }
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


    private void publishEvent(ActionType actionType, String user, HttpServletRequest requestData) {
        UserActionData userActionData = UserActionData
                .builder()
                .user(user)
                .action(actionType)
                .timestamp(Instant.now())
                .ipAddress(requestData.getRemoteAddr())
                .deviceType(requestData.getHeader("User-Agent"))
                .build();

        eventPublisher.publishEvent(new UserActionLog(this, userActionData));
    }
}
