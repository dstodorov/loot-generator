package com.dst.lootgenerator.hero.models.dtos;

import com.dst.lootgenerator.items.models.dtos.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StashDto {
    private Long id;
    private List<ItemDto> items;
    private Long gold;
}
