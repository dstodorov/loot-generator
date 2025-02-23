package com.dst.lootgenerator.core.models;

import org.springframework.http.HttpStatusCode;

import java.time.Instant;

public record SuccessResponse(
        Instant timestamp,
        HttpStatusCode status,
        Object responseBody
) {
}
