package com.dst.lootgenerator.items.models.entities;

import com.dst.lootgenerator.items.models.enums.GemQuality;
import com.dst.lootgenerator.items.models.enums.GemType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "gems")
public class Gem extends Item {
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private GemQuality quality;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private GemType type;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Attribute> attributes = new ArrayList<>();
}
