package com.dst.lootgenerator.items.models.entities;

import com.dst.lootgenerator.items.models.enums.RuneType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "runes")
public class Rune extends Item {
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private RuneType type;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Attribute> attributes = new ArrayList<>();
}
