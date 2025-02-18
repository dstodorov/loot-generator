package com.dst.lootgenerator.admin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "game_configs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accessTokenExpireDuration;
    private Long refreshTokenExpireDuration;
}
