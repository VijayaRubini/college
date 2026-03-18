package com.example.college.exception;

public class InvalidOperationException extends RuntimeException{
    public InvalidOperationException(String message){
        super(message);
    }
}
