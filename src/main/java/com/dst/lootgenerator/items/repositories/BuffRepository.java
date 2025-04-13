package com.dst.lootgenerator.items.repositories;

import com.dst.lootgenerator.hero.models.entities.Buff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuffRepository extends JpaRepository<Buff, Integer> {
}
