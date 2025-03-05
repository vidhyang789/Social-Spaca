package com.example.blog_app.blog_app_exception;

import com.example.blog_app.blog_app_payloads.Apiresponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Apiresponse> resourceNoFoundExceptionHandler(ResourceNotFoundException ex){
        String message = ex.getMessage();
        Apiresponse apiresponse = new Apiresponse(message,false);
        return new ResponseEntity<>(apiresponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handlemethodargsnotvalidexception(MethodArgumentNotValidException ex){
        Map<String,String> resp = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String a = ((FieldError)error).getField();
            String b = error.getDefaultMessage();

            resp.put(a,b);
        });
        return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<Map<String, String>> handleTransactionException(TransactionSystemException ex) {
        Throwable cause = ex.getRootCause();
        Map<String, String> errors = new HashMap<>();

        if (cause instanceof ConstraintViolationException) {
            ConstraintViolationException cve = (ConstraintViolationException) cause;
            cve.getConstraintViolations().forEach(violation -> {
                String field = violation.getPropertyPath().toString();
                String message = violation.getMessage();
                errors.put(field, message);
            });
        } else {
            errors.put("error", "Unexpected transaction error occurred.");
        }

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
