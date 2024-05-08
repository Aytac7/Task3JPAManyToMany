package com.example.task3jpamanytomany.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String code,String message){
        super(message);
    }
}
