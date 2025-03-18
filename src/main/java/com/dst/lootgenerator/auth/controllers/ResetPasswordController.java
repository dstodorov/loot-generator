package com.dst.lootgenerator.auth.controllers;

import com.dst.lootgenerator.auth.models.DTO.ResetPasswordRequest;
import com.dst.lootgenerator.auth.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Controller
public class ResetPasswordController {

    private final AuthService authService;

    public ResetPasswordController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping(value = "/reset-password", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String resetPasswordForm(@RequestParam("token") String token) throws IOException {
        ClassPathResource resource = new ClassPathResource("static/reset-password-form.html");
        String html = FileCopyUtils.copyToString(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
        return html.replace("${token}", token);
    }

    @PostMapping("/update-password")
    public String resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest, HttpServletRequest request) {
        authService.resetPassword(resetPasswordRequest.getToken(), resetPasswordRequest.getNewPassword(), request);
        return "redirect:/password-updated";
    }

    @GetMapping("/password-updated")
    public ModelAndView passwordUpdated() {
        return new ModelAndView("forward:/password-updated.html");
    }
}
