package com.dst.lootgenerator.logger.services;

import com.dst.lootgenerator.logger.models.UserActionData;
import com.dst.lootgenerator.logger.models.UserActionLog;
import com.dst.lootgenerator.logger.repositories.UserActionRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executor;

@Service
public class UserActionLogListener implements ApplicationListener<UserActionLog> {
    private final UserActionRepository userActionRepository;
    private final Executor taskExecutor;

    public UserActionLogListener(UserActionRepository userActionRepository, @Qualifier("taskExecutor") Executor taskExecutor) {
        this.userActionRepository = userActionRepository;
        this.taskExecutor = taskExecutor;
    }

    @Override
    public void onApplicationEvent(UserActionLog event) {
        taskExecutor.execute(() -> logAction(event.getUserData()));
    }

    private void logAction(UserActionData userData) {
        // Write to user actions table
        this.userActionRepository.save(userData);
    }
}
