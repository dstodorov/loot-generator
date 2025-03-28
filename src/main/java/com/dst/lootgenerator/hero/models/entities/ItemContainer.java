package com.dst.lootgenerator.hero.models.entities;

import com.dst.lootgenerator.core.models.BaseEntity;
import com.dst.lootgenerator.items.models.entities.Item;
import com.dst.lootgenerator.items.models.enums.ContainerType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@MappedSuperclass
public abstract class ItemContainer extends BaseEntity {
    private Long gold;
}
