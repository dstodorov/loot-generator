package com.dst.lootgenerator.items.services;

import com.dst.lootgenerator.hero.models.entities.Inventory;
import com.dst.lootgenerator.items.repositories.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public Inventory saveInventory(Inventory inventory) {
        return inventoryRepository.saveAndFlush(inventory);
    }
}
