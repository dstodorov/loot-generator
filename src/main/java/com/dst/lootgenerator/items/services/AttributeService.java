package com.dst.lootgenerator.items.services;

import com.dst.lootgenerator.core.initializers.TestDatasource;
import com.dst.lootgenerator.items.models.entities.Attribute;
import com.dst.lootgenerator.items.models.entities.Item;
import com.dst.lootgenerator.items.repositories.AttributeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttributeService {
    private final AttributeRepository attributeRepository;



    public List<Attribute> saveAll(List<Attribute> attributes) {
        return attributeRepository.saveAllAndFlush(attributes);
    }

    public void deleteAllInBatch() {
        this.attributeRepository.deleteAllInBatch();
    }
}
