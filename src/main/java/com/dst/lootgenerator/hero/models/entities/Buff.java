package com.dst.lootgenerator.hero.models.entities;

import com.dst.lootgenerator.core.models.BaseEntity;
import com.dst.lootgenerator.hero.models.enums.BuffType;
import com.dst.lootgenerator.items.models.entities.Attribute;
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
@Table(name = "buffs")
public class Buff extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BuffType type;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Attribute buffedAttribute;
}
