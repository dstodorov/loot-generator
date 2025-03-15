package com.dst.lootgenerator.core.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class BaseException extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;
}
