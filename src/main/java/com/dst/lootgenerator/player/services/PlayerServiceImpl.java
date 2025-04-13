package com.dst.lootgenerator.player.services;

import com.dst.lootgenerator.auth.repositories.UserRepository;
import com.dst.lootgenerator.player.models.dtos.PlayerHeroDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final UserRepository userRepository;
    @Override
    public List<PlayerHeroDto> getPlayerHeroesBaseInfo(Long id) {
        return this.userRepository.findPlayerHeroesBaseInfo(id);
    }
}
