package com.example.college.exception;

import com.example.college.util.ApiResponse;
import com.example.college.util.ApiStatus;
import com.example.college.util.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleResourceNotFound(ResourceNotFoundException exception){
        return ResponseUtil.build( ApiStatus.NOT_FOUND,exception.getMessage(),null);
        }

    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<ApiResponse<Object>> handleInvalidOperation(InvalidOperationException exception){
        return ResponseUtil.build(ApiStatus.BAD_REQUEST, exception.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleAll(Exception exception){
        return ResponseUtil.build(ApiStatus.INTERNAL_ERROR,"Something went wrong",null);
    }

}
