package com.oceanLife.config;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.oceanLife.model.res.ErrorDTO;
import com.oceanLife.utils.exception.CustomAuthenticationException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomAuthenticationException.class)
    public ResponseEntity<ErrorDTO> handleCustomAuthenticationException(CustomAuthenticationException ex) {
        ErrorDTO errorDto = new ErrorDTO();
        errorDto.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorDto.setError("帳號或密碼錯誤");
        errorDto.setMessage(ex.getMessage());
        
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDto);
    }
	
	
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDTO> handleRuntimeException(RuntimeException  ex, HttpServletRequest request) {
        ErrorDTO errorDto = new ErrorDTO();
        errorDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDto.setError("Internal Server Error");
        errorDto.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
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
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorDTO> handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
    	
        StringBuilder errorMsg = new StringBuilder();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errorMsg.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ");
        }
    	
        ErrorDTO errorDto = new ErrorDTO();
        errorDto.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDto.setError(errorMsg.toString());
        errorDto.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }
    		
}
