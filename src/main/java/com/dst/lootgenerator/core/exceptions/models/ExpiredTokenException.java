package com.dst.lootgenerator.core.exceptions.models;

import com.dst.lootgenerator.core.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class ExpiredTokenException extends BaseException {
    public ExpiredTokenException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
