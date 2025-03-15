package com.dst.lootgenerator.logger.services;

import com.dst.lootgenerator.logger.models.ErrorData;
import com.dst.lootgenerator.logger.models.ErrorLog;
import com.dst.lootgenerator.logger.repositories.ErrorLogRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executor;

@Service
public class ErrorLogListener implements ApplicationListener<ErrorLog> {
    private final ErrorLogRepository errorLogRepository;
    private final Executor taskExecutor;

    public ErrorLogListener(ErrorLogRepository errorLogRepository, @Qualifier("taskExecutor") Executor taskExecutor) {
        this.errorLogRepository = errorLogRepository;
        this.taskExecutor = taskExecutor;
    }

    @Override
    public void onApplicationEvent(ErrorLog event) {
        taskExecutor.execute(() -> logError(event.getErrorData()));
    }

    private void logError(ErrorData errorData) {
        // Write to errors table
        errorLogRepository.save(errorData);
    }
}
