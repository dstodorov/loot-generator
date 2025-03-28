package com.dst.lootgenerator.hero.models.dtos;

import com.dst.lootgenerator.hero.models.entities.Buff;
import com.dst.lootgenerator.hero.models.entities.Effect;
import com.dst.lootgenerator.hero.models.enums.StatusEffect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EffectDto {
    private StatusEffect statusEffect;
    private Effect effectType;
    private String description;
    private Integer damage;
    private Integer heal;
    private Integer damagePerTurn;
    private BuffDto buff;
}
