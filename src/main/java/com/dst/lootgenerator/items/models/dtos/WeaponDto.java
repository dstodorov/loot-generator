package com.dst.lootgenerator.items.models.dtos;

import com.dst.lootgenerator.items.models.enums.ItemRarity;
import com.dst.lootgenerator.items.models.enums.WeaponCategory;
import com.dst.lootgenerator.items.models.enums.WeaponType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class WeaponDto extends ItemDto {
    private ItemRarity itemRarity;
    private WeaponCategory weaponCategory;
    private WeaponType weaponType;
    private List<AttributeDto> attributes;
}
