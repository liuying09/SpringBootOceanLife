package com.oceanLife.utils;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import com.oceanLife.dto.ErrorDTO;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorDTO> handleGlobalException(Exception ex, HttpServletRequest request) {
        ErrorDTO errorDto = new ErrorDTO();
        errorDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDto.setError("Internal Server Error");
        errorDto.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);

    }
    
    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDTO> handleResponseStatusException(ResponseStatusException  ex, HttpServletRequest request) {
        ErrorDTO errorDto = new ErrorDTO();
        errorDto.setStatus(HttpStatus.NOT_FOUND.value());
        errorDto.setError("Not Found");
        errorDto.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }
    
    
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDTO> handleBadRequestException(BadRequestException ex, HttpServletRequest request) {
        ErrorDTO errorDto = new ErrorDTO();
        errorDto.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDto.setError("Bad Request");
        errorDto.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }
}
