package com.dst.lootgenerator.player.controllers;

import com.dst.lootgenerator.player.services.PlayerService;
import com.dst.lootgenerator.core.models.SuccessResponse;
import com.dst.lootgenerator.player.models.dtos.PlayerHeroDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/player")
public class PlayerController {
    private final PlayerService playerService;

    @GetMapping("/heroes")
    public ResponseEntity<SuccessResponse> getHeroes() {
        List<PlayerHeroDto> playerHeroesBaseInfo = this.playerService.getPlayerHeroesBaseInfo();
        return ResponseEntity.ok(new SuccessResponse(
                Instant.now(),
                HttpStatus.ACCEPTED,
                !playerHeroesBaseInfo.isEmpty() ? playerHeroesBaseInfo : "Player does not have any heroes")
        );
    }
}
