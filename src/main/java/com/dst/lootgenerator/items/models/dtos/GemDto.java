package com.dst.lootgenerator.items.models.dtos;

import com.dst.lootgenerator.items.models.enums.GemQuality;
import com.dst.lootgenerator.items.models.enums.GemType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GemDto extends ItemDto {
    private GemQuality quality;
    private GemType type;
    private List<AttributeDto> attributes;
}
