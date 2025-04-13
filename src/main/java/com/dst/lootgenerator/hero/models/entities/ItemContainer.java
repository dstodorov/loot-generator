package com.dst.lootgenerator.hero.models.entities;

import com.dst.lootgenerator.core.models.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
@Getter
public class ItemContainer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long gold;
}
