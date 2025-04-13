package com.dst.lootgenerator.items.repositories;

import com.dst.lootgenerator.items.models.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
