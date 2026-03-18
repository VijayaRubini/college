package com.example.college.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public enum ApiStatus {
    SUCCESS(200,"Success","The Request was successful"),
    CREATED(201,"Created","Request created successfully"),
    UPDATED(200,"Updated","Request updated successfully"),
    DELETED(200,"Deleted","Request deleted successfully"),
    NOT_FOUND(404,"Failure","Resource not found"),
    BAD_REQUEST(400,"Failure","Invalid Request"),
    INTERNAL_ERROR(500,"Error","Internal Server error");

    private final int code;
    private final String header;
    private final String description;



    public int getCode(){
        return code;
    }
    public String getHeader(){
        return header;
    }
    public String getDescription(){
        return description;
    }


}
