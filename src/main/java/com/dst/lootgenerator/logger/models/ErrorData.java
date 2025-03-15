package com.dst.lootgenerator.logger.models;

import com.dst.lootgenerator.core.exceptions.GlobalExceptionHandler;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.ApplicationEvent;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "error_logs")
public class ErrorData implements LogData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant timestamp;
    @Column(columnDefinition = "TEXT")
    private String message;
    private String user;
}
