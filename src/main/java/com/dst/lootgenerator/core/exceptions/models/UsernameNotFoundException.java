package com.dst.lootgenerator.core.exceptions.models;

import com.dst.lootgenerator.core.exceptions.BaseException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UsernameNotFoundException extends BaseException {
    private final String username;

    public UsernameNotFoundException(String message, HttpStatus httpStatus, String username) {
        super(message, httpStatus);
        this.username = username;
    }
}
