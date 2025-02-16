package com.dst.lootgenerator.logger.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogData {
    private String user;
    private ActionType action;
    private String ipAddress;
    private String deviceType;
}
