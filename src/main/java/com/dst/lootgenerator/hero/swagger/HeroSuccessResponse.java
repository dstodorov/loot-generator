package com.dst.lootgenerator.hero.swagger;

import com.dst.lootgenerator.hero.models.dtos.HeroResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Schema(name = "HeroSuccessResponse", description = "Success response containing hero details")
public class HeroSuccessResponse extends GenericSuccessResponse<HeroResponse> {
    public HeroSuccessResponse(Instant timestamp, HttpStatus status, HeroResponse responseBody) {
        super(timestamp, status, responseBody);
    }
}
