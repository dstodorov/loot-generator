package com.dst.lootgenerator.core.exceptions;

import com.dst.lootgenerator.core.exceptions.models.ExpiredTokenException;
import com.dst.lootgenerator.core.exceptions.models.HeroesNotFoundException;
import com.dst.lootgenerator.core.exceptions.models.InvalidTokenException;
import com.dst.lootgenerator.core.exceptions.models.UsernameNotFoundException;
import com.dst.lootgenerator.core.models.FailureResponse;
import com.dst.lootgenerator.logger.models.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.security.Principal;
import java.time.Instant;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;
    private final ApplicationEventPublisher eventPublisher;

    public GlobalExceptionHandler(MessageSource messageSource, ApplicationEventPublisher eventPublisher) {
        this.messageSource = messageSource;
        this.eventPublisher = eventPublisher;
    }

    private ResponseEntity<FailureResponse> createFailureResponse(String message, HttpStatus status, List<String> errors) {
        return new ResponseEntity<>(new FailureResponse(Instant.now(), message, status, errors), status);
    }

    private String getMessage(String messageName, Locale locale) {
        return messageSource.getMessage(messageName, null, locale);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<FailureResponse> handleRuntimeException(RuntimeException exception, Locale locale, HttpServletRequest request) {

        publishEvent(exception, request);

        return createFailureResponse(getMessage("exceptions.unexpected-error.message", locale), HttpStatus.INTERNAL_SERVER_ERROR, List.of(exception.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<FailureResponse> handleBadCredentialsException(BadCredentialsException exception, Locale locale, HttpServletRequest request) {

        publishEvent(exception, request);

        return createFailureResponse(getMessage("exceptions.user-authentication-failed.message", locale), HttpStatus.UNAUTHORIZED, List.of(exception.getMessage()));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<FailureResponse> handleUsernameNotFoundException(UsernameNotFoundException exception, Locale locale, HttpServletRequest request) {

        publishEvent(exception, request);

        return createFailureResponse(getMessage("exceptions.user-not-found.message", locale), HttpStatus.NOT_FOUND, List.of(exception.getMessage()));
    }

    @ExceptionHandler(MailException.class)
    public ResponseEntity<FailureResponse> handleMailException(MailException exception, Locale locale, HttpServletRequest request) {

        publishEvent(exception, request);

        return createFailureResponse(getMessage("exceptions.mail-send-failure.message", locale), HttpStatus.INTERNAL_SERVER_ERROR, List.of(exception.getMessage()));
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<FailureResponse> handleInvalidTokenException(InvalidTokenException exception, Locale locale, HttpServletRequest request) {

        publishEvent(exception, request);

        return createFailureResponse(getMessage("exceptions.invalid-token.message", locale), HttpStatus.UNAUTHORIZED, List.of(exception.getMessage()));
    }

    @ExceptionHandler(ExpiredTokenException.class)
    public ResponseEntity<FailureResponse> handleExpiredTokenException(ExpiredTokenException exception, Locale locale, HttpServletRequest request) {

        publishEvent(exception, request);

        return createFailureResponse(getMessage("exceptions.expired-token.message", locale), HttpStatus.UNAUTHORIZED, List.of(exception.getMessage()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<FailureResponse> handleAccessDeniedException(AccessDeniedException exception, Locale locale, HttpServletRequest request) {
        publishEvent(exception, request);
        return createFailureResponse(getMessage("exceptions.access-denied.message", locale), HttpStatus.FORBIDDEN, List.of(exception.getMessage()));
    }

    @ExceptionHandler(HeroesNotFoundException.class)
    public ResponseEntity<FailureResponse> handleHeroesNotFoundException(HeroesNotFoundException exception, Locale locale, HttpServletRequest request) {
        publishEvent(exception, request);
        return createFailureResponse(getMessage("exceptions.heroes-not-found.message", locale), HttpStatus.NOT_FOUND, List.of(exception.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Locale locale = LocaleContextHolder.getLocale();
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        FailureResponse failureResponse = new FailureResponse(Instant.now(), getMessage("exceptions.validation-error.message", locale), HttpStatus.BAD_REQUEST, errors);

        publishEvent(ex, httpServletRequest);

        return new ResponseEntity<>(failureResponse, HttpStatus.BAD_REQUEST);
    }

    private void publishEvent(Exception exception, HttpServletRequest requestData) {

        Principal userPrincipal = requestData.getUserPrincipal();

        ErrorData errorData = ErrorData
                .builder()
                .timestamp(Instant.now())
                .user(userPrincipal != null ? userPrincipal.getName() : "Anonymous")
                .message(exception.getMessage())
                .build();

        eventPublisher.publishEvent(new ErrorLog(this, errorData));
    }
}