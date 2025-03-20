package com.dst.lootgenerator.items.models.entities;

import com.dst.lootgenerator.items.models.enums.MiscSubType;
import com.dst.lootgenerator.items.models.enums.MiscType;
import com.dst.lootgenerator.items.models.enums.RuneType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
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
