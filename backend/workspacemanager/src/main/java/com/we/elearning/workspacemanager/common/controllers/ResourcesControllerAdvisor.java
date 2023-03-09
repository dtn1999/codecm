package com.we.elearning.workspacemanager.common.controllers;

import com.github.dockerjava.api.exception.NotFoundException;
import com.we.elearning.workspacemanager.common.dtos.ApiResponse;
import com.we.elearning.workspacemanager.common.dtos.ResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class ResourcesControllerAdvisor {

    @ExceptionHandler({NoSuchElementException.class, NotFoundException.class})
    public Mono<ResponseEntity<ApiResponse>> handleElementNotFoundException(RuntimeException exception) {
        log.error("Exception: {}", exception.getMessage());
        return Mono.just(ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ResponseBuilder.error(exception.getMessage(), exception.getMessage())));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Mono<ResponseEntity<ApiResponse>> handleBadRequestException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        String message = "Make sure you have filled all the required fields with valid values";
        return Mono.just(ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseBuilder.error(errors, message)));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ApiResponse>> handleException(Exception exception) {
        log.error("Exception: {}", exception.getMessage());
        return Mono.just(ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseBuilder.error(null, exception.getMessage())));
    }
}
