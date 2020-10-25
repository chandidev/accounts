package com.chandima.accounts.controller;

import com.chandima.accounts.exception.UnauthorizedAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({UnauthorizedAccessException.class})
    public ResponseEntity<Object> handleEntityNotFound(
            UnauthorizedAccessException ex) {
        //create Api Error object and return
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    //any other generic exception handling also goes here.
}
