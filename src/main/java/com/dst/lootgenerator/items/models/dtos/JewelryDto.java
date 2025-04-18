package com.dst.lootgenerator.items.models.dtos;

import com.dst.lootgenerator.items.models.enums.ItemRarity;
import com.dst.lootgenerator.items.models.enums.JewelryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class JewelryDto extends ItemDto {
    private JewelryType type;
    private ItemRarity itemRarity;
    private List<AttributeDto> attributes;
}
