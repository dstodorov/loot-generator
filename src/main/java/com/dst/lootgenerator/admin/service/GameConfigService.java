package com.dst.lootgenerator.admin.service;

import com.dst.lootgenerator.admin.model.GameConfig;
import com.dst.lootgenerator.admin.model.dto.GameConfigRequest;
import com.dst.lootgenerator.admin.repository.GameConfigRepository;
import org.springframework.stereotype.Service;

@Service
public class GameConfigService {

    private final GameConfigRepository gameConfigRepository;

    public GameConfigService(GameConfigRepository gameConfigRepository) {
        this.gameConfigRepository = gameConfigRepository;
    }

    public void updateGameConfig(GameConfigRequest request) {
        Long id = 1L;

        GameConfig config = gameConfigRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Configuration not found"));

        if (request.getAccessTokenExpireDuration() != null) {
            config.setAccessTokenExpireDuration(request.getAccessTokenExpireDuration());
        }

        if (request.getRefreshTokenExpireDuration() != null) {
            config.setRefreshTokenExpireDuration(request.getRefreshTokenExpireDuration());
        }

        gameConfigRepository.save(config);
    }

    public GameConfig getGameConfig() {
        return this.gameConfigRepository
                .findById(1L)
                .orElseThrow(() -> new RuntimeException("Configuration not found"));

    }
}

