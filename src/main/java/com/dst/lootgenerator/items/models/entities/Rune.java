package com.dst.lootgenerator.items.models.entities;

import com.dst.lootgenerator.items.models.enums.RuneType;
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
@Table(name = "runes")
public class Rune extends Item {
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private RuneType type;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "rune_attributes")
    private List<Attribute> attributes = new ArrayList<>();
}
