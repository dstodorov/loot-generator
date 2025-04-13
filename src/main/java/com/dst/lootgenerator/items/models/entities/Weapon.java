package com.dst.lootgenerator.items.models.entities;

import com.dst.lootgenerator.items.models.enums.ItemRarity;
import com.dst.lootgenerator.items.models.enums.WeaponCategory;
import com.dst.lootgenerator.items.models.enums.WeaponType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "weapon_attributes")
    private List<Attribute> attributes = new ArrayList<>();
}
