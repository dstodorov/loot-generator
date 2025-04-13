package com.dst.lootgenerator.items.services;

import com.dst.lootgenerator.hero.models.entities.Stash;
import com.dst.lootgenerator.items.repositories.StashRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StashService {
    private final StashRepository stashRepository;

    public Stash saveStash(Stash stash) {
        return this.stashRepository.saveAndFlush(stash);
    }
}
