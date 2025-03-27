package com.dst.lootgenerator.hero.models.entities;

import com.dst.lootgenerator.core.models.BaseEntity;
import com.dst.lootgenerator.hero.models.enums.BuffType;
import com.dst.lootgenerator.items.models.entities.Attribute;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "buffs")
public class Buff extends BaseEntity {
    private String name;
    private String description;
    private BuffType type;
    @ManyToOne
    private Attribute buffedAttribute;
}
