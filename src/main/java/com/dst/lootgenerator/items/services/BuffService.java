package com.dst.lootgenerator.items.services;

import com.dst.lootgenerator.hero.models.entities.Buff;
import com.dst.lootgenerator.items.repositories.BuffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BuffService {

    private final BuffRepository buffRepository;

    public Buff saveBuff(Buff buff) {
        return buffRepository.saveAndFlush(buff);
    }

    public List<Buff> saveBuffs(List<Buff> buffs) {
        return buffRepository.saveAllAndFlush(buffs);
    }
}
