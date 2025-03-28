package com.dst.lootgenerator.hero.models.dtos;

import com.dst.lootgenerator.items.models.dtos.ArmorDto;
import com.dst.lootgenerator.items.models.dtos.JewelryDto;
import com.dst.lootgenerator.items.models.dtos.OffhandDto;
import com.dst.lootgenerator.items.models.dtos.WeaponDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentDto {
    private Long id;
    private WeaponDto oneHandedWeapon;
    private WeaponDto twoHandedWeapon;
    private ArmorDto bodyArmor;
    private ArmorDto helmet;
    private ArmorDto shield;
    private ArmorDto gloves;
    private ArmorDto boots;
    private OffhandDto offhand;
    private JewelryDto ringOne;
    private JewelryDto ringTwo;
    private JewelryDto amulet;
    private JewelryDto bracelet;
    private JewelryDto necklace;
}
