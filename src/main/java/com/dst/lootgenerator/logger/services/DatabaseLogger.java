package com.dst.lootgenerator.logger.services;

import com.dst.lootgenerator.core.models.FailureResponse;
import com.dst.lootgenerator.logger.models.ErrorLog;
import com.dst.lootgenerator.logger.models.LogData;
import com.dst.lootgenerator.logger.models.UserAction;
import com.dst.lootgenerator.logger.repositories.ErrorLogRepository;
import com.dst.lootgenerator.logger.repositories.UserActionRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class DatabaseLogger implements LoggerService {

    private final UserActionRepository userActionRepository;
    private final ErrorLogRepository errorLogRepository;

    public DatabaseLogger(UserActionRepository userActionRepository, ErrorLogRepository errorLogRepository) {
        this.userActionRepository = userActionRepository;
        this.errorLogRepository = errorLogRepository;
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

    public void logError(FailureResponse failureResponse, String user) {
        ErrorLog errorLog = ErrorLog
                .builder()
                .timestamp(failureResponse.timestamp())
                .user(user)
                .message(String.join("\n", failureResponse.errors()))
                .build();

        this.errorLogRepository.save(errorLog);
    }
}
