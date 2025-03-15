package com.dst.lootgenerator.core.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.boot.autoconfigure.mail.MailProperties;

import java.util.Properties;

@Configuration
@EnableConfigurationProperties(MailProperties.class)
public class AppConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Или друг encoder, който сте избрали
    }

    @Bean
    public JavaMailSender javaMailSender(MailProperties mailProperties) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailProperties.getHost());
        mailSender.setPort(mailProperties.getPort());
        mailSender.setUsername(mailProperties.getUsername());
        mailSender.setPassword(mailProperties.getPassword());
        mailSender.setProtocol(mailProperties.getProtocol());

        Properties props = mailSender.getJavaMailProperties();

        props.put("mail.smtp.auth", mailProperties.getProperties().get("mail.smtp.auth"));
        props.put("mail.smtp.starttls.enable", mailProperties.getProperties().get("mail.smtp.starttls.enable"));
        props.put("mail.smtp.connectiontimeout", mailProperties.getProperties().get("mail.smtp.connectiontimeout"));
        props.put("mail.smtp.timeout", mailProperties.getProperties().get("mail.smtp.timeout"));
        props.put("mail.smtp.writetimeout", mailProperties.getProperties().get("mail.smtp.writetimeout"));

        mailSender.setJavaMailProperties(props);

        return mailSender;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:languages/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
