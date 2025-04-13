package com.dst.lootgenerator.hero.models.dtos;

import com.dst.lootgenerator.hero.models.enums.SchoolType;
import com.dst.lootgenerator.hero.models.enums.SkillActivation;
import com.dst.lootgenerator.hero.models.enums.SkillTarget;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
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
