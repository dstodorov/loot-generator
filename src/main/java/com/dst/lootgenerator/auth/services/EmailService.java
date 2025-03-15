package com.dst.lootgenerator.auth.services;

public interface EmailService {
    void sendPasswordResetEmail(String to, String subject, String token);
}
