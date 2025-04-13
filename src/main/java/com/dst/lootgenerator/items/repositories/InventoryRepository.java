package com.dst.lootgenerator.items.repositories;

import com.dst.lootgenerator.hero.models.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
