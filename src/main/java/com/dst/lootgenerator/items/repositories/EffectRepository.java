package com.dst.lootgenerator.items.repositories;

import com.dst.lootgenerator.hero.models.entities.Effect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EffectRepository extends JpaRepository<Effect, Integer> {
}
