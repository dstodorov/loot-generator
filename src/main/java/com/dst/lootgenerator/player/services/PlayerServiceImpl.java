package com.dst.lootgenerator.player.services;

import com.dst.lootgenerator.auth.models.User;
import com.dst.lootgenerator.auth.repositories.UserRepository;
import com.dst.lootgenerator.auth.services.AuthService;
import com.dst.lootgenerator.core.exceptions.models.HeroesNotFoundException;
import com.dst.lootgenerator.player.models.dtos.PlayerHeroDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final UserRepository userRepository;
    private final AuthService authService;
    @Override
    public List<PlayerHeroDto> getPlayerHeroesBaseInfo() {

        User loggedInUser = authService.getLoggedInUser();

        List<PlayerHeroDto> playerHeroesBaseInfo = this.userRepository.findPlayerHeroesBaseInfo(loggedInUser.getId());

        if(playerHeroesBaseInfo.isEmpty()){
            throw new HeroesNotFoundException("Player does not have any heroes", HttpStatus.NOT_FOUND);
        }

        return playerHeroesBaseInfo;
    }
}
