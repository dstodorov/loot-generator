package com.dst.lootgenerator.items.models.entities;

import com.dst.lootgenerator.items.models.enums.JewelryType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "jewelry")
public class Jewelry extends Item {
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private JewelryType type;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "jewelry_attributes")
    private List<Attribute> attributes = new ArrayList<>();
}
