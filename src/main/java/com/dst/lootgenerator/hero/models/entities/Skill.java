package com.dst.lootgenerator.hero.models.entities;

import com.dst.lootgenerator.core.models.BaseEntity;
import com.dst.lootgenerator.hero.models.enums.SchoolType;
import com.dst.lootgenerator.hero.models.enums.SkillActivation;
import com.dst.lootgenerator.hero.models.enums.SkillTarget;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "skills")
public class Skill extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String iconUrl;
    @Enumerated(EnumType.STRING)
    private SchoolType schoolType;
    private boolean offensive;
    @Enumerated(EnumType.STRING)
    private SkillActivation activation;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Effect> additionalEffects;
    @ManyToOne(fetch = FetchType.EAGER)
    private Effect primaryEffect;
    @Enumerated(EnumType.STRING)
    private SkillTarget target;
    private int cooldown;
    private int cost;
}
