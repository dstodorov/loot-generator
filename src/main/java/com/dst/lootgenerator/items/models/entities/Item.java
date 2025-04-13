package com.dst.lootgenerator.items.models.entities;

import com.dst.lootgenerator.core.models.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@Getter
public abstract class Item extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int level = 1;
}
