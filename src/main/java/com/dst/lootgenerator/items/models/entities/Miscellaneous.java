package com.dst.lootgenerator.items.models.entities;

import com.dst.lootgenerator.items.models.enums.MiscSubType;
import com.dst.lootgenerator.items.models.enums.MiscType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "miscellaneous")
public class Miscellaneous extends Item {
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private MiscType type;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private MiscSubType subType;
}
