package com.dst.lootgenerator.hero.models.entities;

import com.dst.lootgenerator.core.models.BaseEntity;
import com.dst.lootgenerator.hero.models.enums.HeroClass;
import com.dst.lootgenerator.items.models.entities.Attribute;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "heroes")
@Data
public class Hero extends BaseEntity {
    private String name;
    private int level = 1;
    private long experience = 0;
    @Enumerated(EnumType.STRING)
    private HeroClass heroClass;
    @OneToOne
    @JoinColumn(name = "stash_id")
    private Stash stash;
    @OneToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;
    @OneToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;
    @OneToMany
    @JoinTable(name = "hero_stats")
    private List<Attribute> stats;
    @OneToMany
    @JoinTable(name = "hero_skills")
    private List<Skill> skills;
}
