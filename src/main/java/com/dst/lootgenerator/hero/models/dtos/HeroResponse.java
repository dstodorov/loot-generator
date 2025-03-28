package com.dst.lootgenerator.hero.models.dtos;

import com.dst.lootgenerator.hero.models.enums.HeroClass;
import com.dst.lootgenerator.items.models.dtos.AttributeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HeroResponse {
    private Long id;
    private String name;
    private int level;
    private long experience;
    private HeroClass heroClass;
    private StashDto stash;
    private InventoryDto inventory;
    private EquipmentDto equipment;
    private List<AttributeDto> attributes;
    private List<SkillDto> skills;
}
