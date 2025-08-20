package com.dst.lootgenerator.hero.services;

import com.dst.lootgenerator.hero.models.dtos.HeroResponse;
import com.dst.lootgenerator.hero.models.entities.Hero;
import com.dst.lootgenerator.hero.models.enums.HeroClass;

public interface HeroService {
    Hero saveHero(Hero hero);

    Hero createHero(String name, HeroClass heroClass);

    HeroResponse getHeroById(Long heroId);
}
