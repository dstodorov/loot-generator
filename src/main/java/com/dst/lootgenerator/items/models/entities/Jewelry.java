package com.dst.lootgenerator.items.models.entities;

import com.dst.lootgenerator.items.models.enums.ItemRarity;
import com.dst.lootgenerator.items.models.enums.JewelryType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "jewelry")
public class Jewelry extends Item {
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private JewelryType type;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ItemRarity rarity;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "jewelry_attributes")
    private List<Attribute> attributes = new ArrayList<>();
}
