package com.neoteric.avoota_inventory.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(HotelNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleHotelNotFound(HotelNotFoundException ex) {
        log.error("Hotel not found: {}", ex.getMessage());
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRoomNotFound(RoomNotFoundException ex) {
        log.error("Room not found: {}", ex.getMessage());
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        log.error("Resource not found: {}", ex.getMessage());
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
        log.error("Bad request: {}", ex.getMessage());
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        log.warn("Validation failed");

        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        log.error("Internal server error", ex);
        return buildErrorResponse("Something went wrong. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(String message, HttpStatus status) {
        return new ResponseEntity<>(
                new ErrorResponse(message, status.value(), LocalDateTime.now()),
                status
        );
    }
}
