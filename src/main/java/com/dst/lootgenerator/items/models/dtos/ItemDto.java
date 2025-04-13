package com.dst.lootgenerator.items.models.dtos;

import com.dst.lootgenerator.items.models.enums.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ItemDto {
    private Long id;
    private String name;
    private List<AttributeDto> attributes = new ArrayList<>();
    private int level = 1;
    private ArmorType armorType;
    private CharmType charmType;
    private GemQuality gemQuality;
    private GemType gemType;
    private ItemRarity rarity;
    private JewelryType jewelryType;
    private MiscType miscType;
    private MiscSubType miscSubType;
    private PotionType potionType;
    private Integer potionRestoreValue;
    private RuneType runeType;
    private WeaponCategory weaponCategory;
    private WeaponType weaponType;
    private PotionSize potionSize;
}
