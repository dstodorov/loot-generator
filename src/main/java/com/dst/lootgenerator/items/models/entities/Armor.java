package com.dst.lootgenerator.items.models.entities;

import com.dst.lootgenerator.items.models.enums.ArmorType;
import com.dst.lootgenerator.items.models.enums.ItemRarity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "armors")
public class Armor extends Item{
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ItemRarity rarity;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ArmorType type;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "armor_attributes")
    private List<Attribute> attributes = new ArrayList<>();
}
