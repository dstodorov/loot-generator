package com.dst.lootgenerator.admin.controller;

import com.dst.lootgenerator.admin.model.GameConfig;
import com.dst.lootgenerator.admin.model.dto.GameConfigRequest;
import com.dst.lootgenerator.admin.service.GameConfigService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/config")
public class GameConfigController {
    private final GameConfigService gameConfigService;

    public GameConfigController(GameConfigService gameConfigService) {
        this.gameConfigService = gameConfigService;
    }

    @PatchMapping("/update")
    public ResponseEntity<?> update(@RequestBody @Valid GameConfigRequest GameConfigRequest) {
        this.gameConfigService.updateGameConfig(GameConfigRequest);

        return ResponseEntity.ok().build();
    }
}
