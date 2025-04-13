package com.dst.lootgenerator.items.models.entities;

import com.dst.lootgenerator.items.models.enums.ArmorType;
import com.dst.lootgenerator.items.models.enums.ItemRarity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "armors")
public class Armor extends Item{
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ItemRarity rarity;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ArmorType type;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "armor_attributes")
    private List<Attribute> attributes = new ArrayList<>();
}
