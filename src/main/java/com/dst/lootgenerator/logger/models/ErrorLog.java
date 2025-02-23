package com.dst.lootgenerator.logger.models;

import com.dst.lootgenerator.auth.models.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "error_logs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant timestamp;
    private String message;
    private String user;
}
