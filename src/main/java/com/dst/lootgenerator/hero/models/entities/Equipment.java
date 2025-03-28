package com.dst.lootgenerator.hero.models.entities;

import com.dst.lootgenerator.core.models.BaseEntity;
import com.dst.lootgenerator.items.models.entities.Armor;
import com.dst.lootgenerator.items.models.entities.Jewelry;
import com.dst.lootgenerator.items.models.entities.Offhand;
import com.dst.lootgenerator.items.models.entities.Weapon;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "equipments")
public class Equipment extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "hero_id")
    private Hero hero;
    @OneToOne
    @JoinColumn(name = "one_handed_weapon_id")
    private Weapon oneHandedWeapon;
    @OneToOne
    @JoinColumn(name = "two_handed_weapon_id")
    private Weapon twoHandedWeapon;
    @OneToOne
    @JoinColumn(name = "body_armor_id")
    private Armor bodyArmor;
    @OneToOne
    @JoinColumn(name = "helmet_id")
    private Armor helmet;
    @OneToOne
    @JoinColumn(name = "shield_id")
    private Armor shield;
    @OneToOne
    @JoinColumn(name = "offhand_id")
    private Offhand offhand;
    @OneToOne
    @JoinColumn(name = "gloves_id")
    private Armor gloves;
    @OneToOne
    @JoinColumn(name = "boots_id")
    private Armor boots;
    @OneToOne
    @JoinColumn(name = "ring_one_id")
    private Jewelry ringOne;
    @OneToOne
    @JoinColumn(name = "ring_two_id")
    private Jewelry ringTwo;
    @OneToOne
    @JoinColumn(name = "amulet_id")
    private Jewelry amulet;
    @OneToOne
    @JoinColumn(name = "bracelet_id")
    private Jewelry bracelet;
    @OneToOne
    @JoinColumn(name = "necklace_id")
    private Jewelry necklace;
}
