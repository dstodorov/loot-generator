package com.dst.lootgenerator.core.exceptions.models;

import com.dst.lootgenerator.core.exceptions.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class InvalidTokenException extends BaseException {
    public InvalidTokenException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
