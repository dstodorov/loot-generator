package com.dst.lootgenerator.logger.models;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserActionLog extends ApplicationEvent {
    private final UserActionData userData;
    public UserActionLog(Object source, UserActionData userData) {
        super(source);
        this.userData = userData;
    }
}
