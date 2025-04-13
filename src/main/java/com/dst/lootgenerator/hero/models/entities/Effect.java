package com.dst.lootgenerator.hero.models.entities;

import com.dst.lootgenerator.core.models.BaseEntity;
import com.dst.lootgenerator.hero.models.enums.EffectType;
import com.dst.lootgenerator.hero.models.enums.StatusEffect;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "status_effects")
public class Effect extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private StatusEffect statusEffect;
    @Enumerated(EnumType.STRING)
    private EffectType effectType;
    private String description;
    private int damagePerTurn;
    private int heal;
    @ManyToOne(fetch = FetchType.EAGER)
    private Buff buff;
}
