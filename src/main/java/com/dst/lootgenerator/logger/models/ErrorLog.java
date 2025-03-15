package com.dst.lootgenerator.logger.models;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ErrorLog extends ApplicationEvent {
    private final ErrorData errorData;
    public ErrorLog(Object source, ErrorData errorData) {
        super(source);
        this.errorData = errorData;
    }
}
