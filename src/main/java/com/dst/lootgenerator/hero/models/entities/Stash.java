package com.dst.lootgenerator.hero.models.entities;

import com.dst.lootgenerator.items.models.entities.Item;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "stashes")
public class Stash extends ItemContainer {

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "stash_items", joinColumns = @JoinColumn(name = "stash_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();
}
