package com.dst.lootgenerator.hero.controllers;

import com.dst.lootgenerator.core.models.SuccessResponse;
import com.dst.lootgenerator.hero.models.dtos.HeroResponse;
import com.dst.lootgenerator.hero.services.HeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hero")
public class HeroController {

    private final HeroService heroService;

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse> getHero(@PathVariable Long id) {
        HeroResponse heroDetails = heroService.getHeroById(id);
        return heroDetails != null ? ResponseEntity.ok(new SuccessResponse(
                Instant.now(), HttpStatus.OK,
                heroDetails
        )) : new ResponseEntity<>(new SuccessResponse(
                Instant.now(),
                HttpStatus.NOT_FOUND,
                "Hero not found"
        ), HttpStatus.NOT_FOUND);
    }
}
