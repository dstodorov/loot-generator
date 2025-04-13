package com.dst.lootgenerator.items.services;

import com.dst.lootgenerator.hero.models.entities.Effect;
import com.dst.lootgenerator.hero.models.entities.Equipment;
import com.dst.lootgenerator.items.repositories.EffectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EffectService {
    private final EffectRepository effectRepository;

    public Effect saveEffect(Effect equipment) {
        return effectRepository.saveAndFlush(equipment);
    }

    public List<Effect> saveEffects(List<Effect> effects) {
        return effectRepository.saveAllAndFlush(effects);
    }
}
