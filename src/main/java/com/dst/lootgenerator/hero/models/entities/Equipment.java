package com.dst.lootgenerator.hero.models.entities;

import com.dst.lootgenerator.core.models.BaseEntity;
import com.dst.lootgenerator.items.models.entities.Armor;
import com.dst.lootgenerator.items.models.entities.Jewelry;
import com.dst.lootgenerator.items.models.entities.Offhand;
import com.dst.lootgenerator.items.models.entities.Weapon;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "equipments")
public class Equipment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @OneToOne
//    @JoinColumn(name = "hero_id")
//    private Hero hero;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "one_handed_weapon_id")
    private Weapon oneHandedWeapon;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "two_handed_weapon_id")
    private Weapon twoHandedWeapon;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "body_armor_id")
    private Armor bodyArmor;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "helmet_id")
    private Armor helmet;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "shield_id")
    private Armor shield;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "offhand_id")
    private Offhand offhand;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "gloves_id")
    private Armor gloves;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "boots_id")
    private Armor boots;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ring_one_id")
    private Jewelry ringOne;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ring_two_id")
    private Jewelry ringTwo;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "amulet_id")
    private Jewelry amulet;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "bracelet_id")
    private Jewelry bracelet;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "necklace_id")
    private Jewelry necklace;
}
