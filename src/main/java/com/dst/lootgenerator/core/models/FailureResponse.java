package com.dst.lootgenerator.core.models;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.time.Instant;
import java.util.List;

public record FailureResponse(
        Instant timestamp,
        String message,
        HttpStatusCode status,
        List<String> errors
) {
}
