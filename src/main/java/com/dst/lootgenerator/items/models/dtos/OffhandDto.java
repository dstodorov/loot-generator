package com.dst.lootgenerator.items.models.dtos;

import com.dst.lootgenerator.items.models.enums.ItemRarity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OffhandDto extends ItemDto {
    private ItemRarity rarity;
    private List<AttributeDto> attributes;
}
