package com.dst.lootgenerator.items.models.entities;

import com.dst.lootgenerator.items.models.enums.CharmType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "charms")
public class Charm extends Item {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CharmType type;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "charm_attributes")
    private List<Attribute> attributes = new ArrayList<>();
}
