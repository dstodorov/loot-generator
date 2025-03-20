package com.dst.lootgenerator.items.models.entities;

import com.dst.lootgenerator.core.models.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
public abstract class Item extends BaseEntity {
    @Column(nullable = false)
    private String name;
    private int level = 1;
}
