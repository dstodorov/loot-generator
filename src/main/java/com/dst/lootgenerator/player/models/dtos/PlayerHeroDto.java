package com.dst.lootgenerator.player.models.dtos;

import com.dst.lootgenerator.hero.models.enums.HeroClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerHeroDto {
    private Long id;
    private String name;
    private int level;
    private HeroClass heroClass;
}
