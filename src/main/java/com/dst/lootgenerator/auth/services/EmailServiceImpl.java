package com.dst.lootgenerator.auth.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendPasswordResetEmail(String to, String subject, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText("Click the link below to reset your password:\n\n" +
                "https://codesharing.org/lootgenerator/reset-password?token=" + token);
        message.setFrom("support@codesharing.org");

        mailSender.send(message);
    }
}
