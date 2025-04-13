package com.dst.lootgenerator.items.services;

import com.dst.lootgenerator.items.models.entities.Item;
import com.dst.lootgenerator.items.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public List<Item> saveAll(List<Item> items) {
        return itemRepository.saveAllAndFlush(items);
    }

    public void deleteAllInBatch() {
        this.itemRepository.deleteAllInBatch();
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }
}
