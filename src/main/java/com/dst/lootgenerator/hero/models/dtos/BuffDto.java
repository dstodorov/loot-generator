package com.dst.lootgenerator.hero.models.dtos;

import com.dst.lootgenerator.hero.models.enums.BuffType;
import com.dst.lootgenerator.hero.models.enums.EffectType;
import com.dst.lootgenerator.hero.models.enums.StatusEffect;
import com.dst.lootgenerator.items.models.dtos.AttributeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuffDto {
    private String name;
    private BuffType type;
    private String description;
    private AttributeDto buffedAttribute;
}
