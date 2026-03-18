package com.example.college.util;

import org.springframework.http.ResponseEntity;

public class ResponseUtil {
    public static <T>ResponseEntity<ApiResponse<T>> build(ApiStatus apiStatus,String message,T data){
        StatusDto statusDto=new StatusDto(apiStatus.getCode(),apiStatus.getHeader(),apiStatus.getDescription(),message);
        ApiResponse<T> response = new ApiResponse<>(statusDto,data);

        return ResponseEntity
                .status(apiStatus.getCode())
                .body(response);

    }
}
