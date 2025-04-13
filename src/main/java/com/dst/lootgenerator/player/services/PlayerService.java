package com.dst.lootgenerator.player.services;

import com.dst.lootgenerator.player.models.dtos.PlayerHeroDto;

import java.util.List;


public interface PlayerService {
    List<PlayerHeroDto> getPlayerHeroesBaseInfo(Long id);
}
