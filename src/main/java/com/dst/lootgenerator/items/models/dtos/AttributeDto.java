package com.dst.lootgenerator.items.models.dtos;

import com.dst.lootgenerator.items.models.enums.ValueType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttributeDto {
    private String name;
    private ValueType valueType;
    private float value;
    private String description;
}
