package com.hingehealth.demo.exception;

import com.hingehealth.demo.enums.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<?> handleApplicationExceptions(ApplicationException exception, HttpServletRequest request) {
        String guid = UUID.randomUUID().toString();
        log.error(String.format("Application Exception occurred: GUID - %s, error message - %s", guid, exception.getMessage()));
        ExceptionResponse response = ExceptionResponse.builder()
                .guid(guid)
                .errorCode(exception.getErrorCode())
                .message(exception.getMessage())
                .statusCode(exception.getHttpStatus().value())
                .statusName(exception.getHttpStatus().name())
                .path(request.getRequestURI())
                .method(request.getMethod())
                .timestamp(LocalDateTime.now()).build();
        return new ResponseEntity<>(response, exception.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String guid = UUID.randomUUID().toString();
        List<String> errorMessages = exception.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        log.error(String.format("MethodArgumentNotValidException occurred : GUID - %s, error message - %s", guid, errorMessages.toString()));
        ExceptionResponse response = ExceptionResponse.builder()
                .guid(guid)
                .errorCode(ErrorCode.BAD_REQUEST.getValue())
                .message(errorMessages.toString())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .statusName(HttpStatus.BAD_REQUEST.name())
                .path(request.getContextPath())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUnknownExceptions(Exception exception, HttpServletRequest request) {
        String guid = UUID.randomUUID().toString();
        log.error(String.format("Exception occurred: GUID - %s, error message - %s", guid, exception.getMessage()));
        ExceptionResponse response = ExceptionResponse.builder()
                .guid(guid)
                .errorCode(ErrorCode.INTERNAL_ERROR.getValue())
                .message("Internal server error")
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .statusName(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .path(request.getRequestURI())
                .method(request.getMethod())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}