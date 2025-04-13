package com.dst.lootgenerator.items.repositories;

import com.dst.lootgenerator.hero.models.entities.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
}
