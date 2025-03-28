package com.dst.lootgenerator.items.models.dtos;

import com.dst.lootgenerator.items.models.enums.MiscSubType;
import com.dst.lootgenerator.items.models.enums.MiscType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MiscellaneousDto extends ItemDto {
    private MiscType miscType;
    private MiscSubType miscSubType;
}
