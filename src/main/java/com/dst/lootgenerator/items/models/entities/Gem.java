package com.dst.lootgenerator.items.models.entities;

import com.dst.lootgenerator.items.models.enums.GemQuality;
import com.dst.lootgenerator.items.models.enums.GemType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "gems")
public class Gem extends Item {
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private GemQuality quality;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private GemType type;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "gem_attributes")
    private List<Attribute> attributes = new ArrayList<>();
}
