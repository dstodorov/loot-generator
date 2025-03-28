package com.dst.lootgenerator.items.models.dtos;

import com.dst.lootgenerator.items.models.enums.CharmType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CharmDto extends ItemDto {
    private CharmType charmType;
    private List<AttributeDto> attributes;
}
