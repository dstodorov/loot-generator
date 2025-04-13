package com.dst.lootgenerator.items.repositories;

import com.dst.lootgenerator.hero.models.entities.Stash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StashRepository extends JpaRepository<Stash, Long> {
}
