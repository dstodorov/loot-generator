package com.dst.lootgenerator.items.models.dtos;

import com.dst.lootgenerator.items.models.enums.RuneType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuneDto extends ItemDto{
    private RuneType type;
    private List<AttributeDto> attributes;
}
