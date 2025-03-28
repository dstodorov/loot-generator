package com.dst.lootgenerator.items.models.dtos;

import com.dst.lootgenerator.items.models.enums.PotionSize;
import com.dst.lootgenerator.items.models.enums.PotionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PotionDto extends ItemDto {
    private PotionSize size;
    private PotionType type;
    private int restoreValue;
}
