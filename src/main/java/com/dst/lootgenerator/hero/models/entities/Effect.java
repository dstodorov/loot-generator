package com.dst.lootgenerator.hero.models.entities;

import com.dst.lootgenerator.core.models.BaseEntity;
import com.dst.lootgenerator.hero.models.enums.EffectType;
import com.dst.lootgenerator.hero.models.enums.StatusEffect;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "status_effects")
public class Effect extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private StatusEffect statusEffect;
    @Enumerated(EnumType.STRING)
    private EffectType effectType;
    private String description;
    private int damagePerTurn;
    private int heal;
    @ManyToOne
    private Buff buff;
}
