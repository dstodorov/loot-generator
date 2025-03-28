package com.dst.lootgenerator.hero.models.dtos;

import com.dst.lootgenerator.hero.models.enums.SchoolType;
import com.dst.lootgenerator.hero.models.enums.SkillActivation;
import com.dst.lootgenerator.hero.models.enums.SkillTarget;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkillDto {
    private Long id;
    private String name;
    private String iconUrl;
    private SchoolType schoolType;
    private boolean offensive;
    private SkillActivation activation;
    private EffectDto primaryEffect;
    private List<EffectDto> additionalEffects;
    private SkillTarget target;
    private int cooldown;
    private int cost;
}
