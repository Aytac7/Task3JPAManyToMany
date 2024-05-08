package com.example.task3jpamanytomany.exception.handler;

import com.example.task3jpamanytomany.exception.DoctorIdNotFoundException;
import com.example.task3jpamanytomany.exception.NotFoundException;
import com.example.task3jpamanytomany.exception.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
@Slf4j
public class CustomerException {

    @ExceptionHandler(DoctorIdNotFoundException.class)
    @ResponseStatus(NOT_FOUND)

    public ErrorResponse handlerDoctorIdNotFoundException(Exception exception){
        log.info("handlerDoctorIdNotFoundException", exception.getMessage());
        return ErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.name())
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handlerNotFoundException(NotFoundException exception){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.name(),exception.getMessage() );
    }




}