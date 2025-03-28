package com.dst.lootgenerator.items.models.dtos;

import com.dst.lootgenerator.items.models.enums.ValueType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AttributeDto extends ItemDto {
    private String name;
    private ValueType valueType;
    private float value;
    private String description;
}
