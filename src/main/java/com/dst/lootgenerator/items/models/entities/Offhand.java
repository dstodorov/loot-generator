package com.dst.lootgenerator.items.models.entities;

import com.dst.lootgenerator.items.models.enums.ItemRarity;
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
@Table(name = "offhands")
public class Offhand extends Item {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ItemRarity rarity;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "offhand_attributes")
    private List<Attribute> attributes = new ArrayList<>();
}
