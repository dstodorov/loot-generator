package com.dst.lootgenerator.hero.controllers;

import com.dst.lootgenerator.core.models.FailureResponse;
import com.dst.lootgenerator.hero.models.dtos.HeroResponse;
import com.dst.lootgenerator.hero.services.HeroService;
import com.dst.lootgenerator.hero.swagger.GenericSuccessResponse;
import com.dst.lootgenerator.hero.swagger.HeroSuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hero")
@Tag(name = "Hero Controller", description = "API endpoints related to hero operations")
public class HeroController {

    private final HeroService heroService;

    @Operation(summary = "Get hero by ID", description = "Returns hero details if the ID exists.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved hero",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = HeroSuccessResponse.class)))
    })
    @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = FailureResponse.class)))
    @GetMapping("/{heroId}")
    public ResponseEntity<GenericSuccessResponse<HeroResponse>> getHero(@PathVariable Long heroId) {
        HeroResponse heroDetails = heroService.getHeroById(heroId);

        return ResponseEntity.ok(new GenericSuccessResponse<>(Instant.now(), HttpStatus.OK, heroDetails));
    }
}
