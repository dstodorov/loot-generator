package com.dst.lootgenerator.logger.services;

import com.dst.lootgenerator.logger.models.LogData;
import com.dst.lootgenerator.logger.models.UserAction;
import com.dst.lootgenerator.logger.repositories.UserActionRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class DatabaseLogger implements LoggerService {

    private final UserActionRepository userActionRepository;

    public DatabaseLogger(UserActionRepository userActionRepository) {
        this.userActionRepository = userActionRepository;
    }

    @Override
    public void log(LogData logData) {
        UserAction userAction = UserAction
                .builder()
                .user(logData.getUser())
                .action(logData.getAction())
                .ipAddress(logData.getIpAddress())
                .deviceType(logData.getDeviceType())
                .timestamp(Instant.now())
                .build();

        this.userActionRepository.save(userAction);
    }
}
