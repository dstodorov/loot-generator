package com.dst.lootgenerator.hero.models;

import com.dst.lootgenerator.core.models.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "heroes")
@Data
public class Hero extends BaseEntity {
}
