package com.dst.lootgenerator.items.models.dtos;

import com.dst.lootgenerator.items.models.enums.ArmorType;
import com.dst.lootgenerator.items.models.enums.ItemRarity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArmorDto extends ItemDto {
    private ItemRarity itemRarity;
    private ArmorType armorType;
    private List<AttributeDto> attributes;
}
