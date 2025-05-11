package com.dst.lootgenerator.hero.swagger;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@Schema(name = "GenericSuccessResponse", description = "Generic success response wrapper")
public class GenericSuccessResponse<T> {

    @Schema(description = "Timestamp of the response")
    private final Instant timestamp;

    @Schema(description = "HTTP status code")
    private final HttpStatus status;

    @Schema(description = "The actual response body")
    private final T responseBody;
}
