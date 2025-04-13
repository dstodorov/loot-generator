package com.dst.lootgenerator.items.models.entities;

import com.dst.lootgenerator.core.models.BaseEntity;
import com.dst.lootgenerator.items.models.enums.ValueType;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "attributes")
public class Attribute extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    private ValueType valueType;
    @Column(nullable = false)
    private float value;
    private String description;
}
