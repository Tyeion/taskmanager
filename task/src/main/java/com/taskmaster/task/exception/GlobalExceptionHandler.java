package com.taskmaster.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice

public class GlobalExceptionHandler {

   @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<String> handleNotFound(TaskNotFoundException ex)
    {
        return new ResponseEntity<>(ex.getMessage() , HttpStatus.NOT_FOUND);
    }
}
