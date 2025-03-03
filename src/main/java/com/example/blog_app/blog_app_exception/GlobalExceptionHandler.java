package com.example.blog_app.blog_app_exception;

import com.example.blog_app.blog_app_payloads.Apiresponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Apiresponse> resourceNoFoundExceptionHandler(ResourceNotFoundException ex){
        String message = ex.getMessage();
        Apiresponse apiresponse = new Apiresponse(message,false);
        return new ResponseEntity<>(apiresponse, HttpStatus.NOT_FOUND);
    }

}
