package com.we.elearning.common.controllers;

import com.we.elearning.common.dtos.ApiResponse;
import com.we.elearning.common.dtos.ResponseBuilder;
import com.we.elearning.playgrounds.exceptions.PlaygroundManagementException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class ResourcesControllerAdvisor {
    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<ApiResponse> handleElementNotFoundException(NoSuchElementException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ResponseBuilder.error(exception.getMessage(), exception.getMessage()));
    }

    @ExceptionHandler({PlaygroundManagementException.class})
    public ResponseEntity<ApiResponse> handlePlaygroundNotCreatedException(PlaygroundManagementException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseBuilder.error(exception.getMessage(),
                        "Try again later, or contact admin: ngongangtchekambou@gmail.com"));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ApiResponse> handleBadRequestException(WebExchangeBindException exception) {
        Map<String, String> errors = new HashMap<>();
        String message = "Make sure you have filled all the required fields with valid values";
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        log.error("Bad request. Error: {}", errors);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseBuilder.error(errors, message));
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseBuilder.error(exception.getMessage()));
    }
}
