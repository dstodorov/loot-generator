package com.dst.lootgenerator.hero.models.entities;

import com.dst.lootgenerator.core.models.BaseEntity;
import com.dst.lootgenerator.hero.models.enums.HeroClass;
import com.dst.lootgenerator.items.models.entities.Attribute;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@SuperBuilder
@NoArgsConstructor
@Table(name = "heroes")
@Data
public class Hero extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int level = 1;
    private long experience = 0;
    @Enumerated(EnumType.STRING)
    private HeroClass heroClass;
    @OneToOne
    @JoinColumn(name = "stash_id")
    private Stash stash;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "hero_stats")
    private List<Attribute> stats;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "hero_skills")
    private List<Skill> skills;
}
