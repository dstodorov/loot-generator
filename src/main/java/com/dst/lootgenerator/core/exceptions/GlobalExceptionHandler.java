package com.dst.lootgenerator.core.exceptions;

import com.dst.lootgenerator.core.models.FailureResponse;
import com.dst.lootgenerator.logger.repositories.ErrorLogRepository;
import com.dst.lootgenerator.logger.services.DatabaseLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final DatabaseLogger logger;
    private final DatabaseLogger databaseLogger;

    public GlobalExceptionHandler(DatabaseLogger logger, DatabaseLogger databaseLogger) {
        this.logger = logger;
        this.databaseLogger = databaseLogger;
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<FailureResponse> handleBaseException(BaseException exception) {
        log.error("BaseException occurred: {}", exception.getMessage(), exception);
        List<String> errors = List.of(exception.getMessage());
        return new ResponseEntity<>(new FailureResponse(Instant.now(), exception.getMessage(), exception.getHttpStatusCode(), errors), exception.getHttpStatusCode());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<FailureResponse> handleRuntimeException(RuntimeException exception) {
        log.error("RuntimeException occurred: {}", exception.getMessage(), exception);
        List<String> errors = List.of(exception.getMessage());
        return new ResponseEntity<>(new FailureResponse(Instant.now(), "An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR, errors), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<FailureResponse> handleBadCredentialsException(BadCredentialsException exception) {
        //log.error("BadCredentialsException occurred: {}", exception.getMessage(), exception);
        List<String> errors = List.of(exception.getMessage());
        FailureResponse userAuthenticationFailed = new FailureResponse(Instant.now(), "User authentication failed", HttpStatus.UNAUTHORIZED, errors);
        databaseLogger.logError(userAuthenticationFailed, "");
        return new ResponseEntity<>(userAuthenticationFailed, HttpStatus.UNAUTHORIZED);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        log.warn("Validation errors: {}", errors);

        FailureResponse failureResponse = new FailureResponse(Instant.now(), "Validation failed", HttpStatus.BAD_REQUEST, errors);
        return new ResponseEntity<>(failureResponse, HttpStatus.BAD_REQUEST);
    }
}
