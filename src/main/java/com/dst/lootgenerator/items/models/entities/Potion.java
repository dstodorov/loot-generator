package com.dst.lootgenerator.items.models.entities;

import com.dst.lootgenerator.items.models.enums.PotionSize;
import com.dst.lootgenerator.items.models.enums.PotionType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "potions")
public class Potion extends Item {
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PotionSize size;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PotionType type;
    @Column(nullable = false)
    private Integer restoreValue;
}
