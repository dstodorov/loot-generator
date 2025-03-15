package com.dst.lootgenerator.logger.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "logs_user_actions")
public class UserActionData implements LogData{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant timestamp;

    private String user;

    @Enumerated(EnumType.STRING)
    private ActionType action;

    private String ipAddress;

    private String deviceType;
}
