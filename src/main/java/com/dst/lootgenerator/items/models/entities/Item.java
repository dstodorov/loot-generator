package com.dst.lootgenerator.items.models.entities;

import com.dst.lootgenerator.core.models.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Item extends BaseEntity {
    @Column(nullable = false)
    private String name;
    private int level = 1;
}
