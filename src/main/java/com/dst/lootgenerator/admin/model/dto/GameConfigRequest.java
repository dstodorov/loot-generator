package com.dst.lootgenerator.admin.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameConfigRequest {
    Long accessTokenExpireDuration = null;
    Long refreshTokenExpireDuration = null;
}
