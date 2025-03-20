package com.dst.lootgenerator.items.models.entities;

import com.dst.lootgenerator.items.models.enums.ItemRarity;
import com.dst.lootgenerator.items.models.enums.WeaponCategory;
import com.dst.lootgenerator.items.models.enums.WeaponType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "weapons")
public class Weapon extends Item {
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ItemRarity rarity;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private WeaponCategory category;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private WeaponType type;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Attribute> attributes = new ArrayList<>();
}
