package com.dst.lootgenerator.items.services;

import com.dst.lootgenerator.hero.models.entities.Equipment;
import com.dst.lootgenerator.items.repositories.EquipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;

    public Equipment saveEquipment(Equipment equipment) {
        return equipmentRepository.saveAndFlush(equipment);
    }
}
