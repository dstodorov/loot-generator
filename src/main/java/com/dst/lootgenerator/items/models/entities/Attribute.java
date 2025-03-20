package com.dst.lootgenerator.items.models.entities;

import com.dst.lootgenerator.core.models.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "attributes")
public class Attribute extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(name = "value_type", nullable = false)
    private Integer valueType;
    @Column(nullable = false)
    private String value;
    private String description;
}
