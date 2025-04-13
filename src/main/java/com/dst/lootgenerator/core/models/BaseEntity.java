package com.dst.lootgenerator.core.models;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
public abstract class BaseEntity {
    @Version
    private Long version;
}
