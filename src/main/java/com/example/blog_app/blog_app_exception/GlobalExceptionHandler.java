package com.example.blog_app.blog_app_exception;

import com.example.blog_app.blog_app_payloads.Apiresponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

        System.out.println("TransactionSystemException caught: " + ex.getMessage()); // Print main exception
        ex.printStackTrace(); // Print full stack trace for debugging

        if (cause instanceof ConstraintViolationException) {
            ConstraintViolationException cve = (ConstraintViolationException) cause;
            System.out.println("ConstraintViolationException: " + cve.getMessage()); // Print cause details

            cve.getConstraintViolations().forEach(violation -> {
                String field = violation.getPropertyPath().toString();
                String message = violation.getMessage();
                errors.put(field, message);

                // Print each violation
                System.out.println("Field: " + field + " - Error: " + message);
            });
        } else {
            errors.put("error", "Unexpected transaction error occurred.");
            System.out.println("Unexpected transaction error occurred: " + cause);
        }

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String errorResponse;
        errorResponse = "This email is already in use. Please choose another one.";
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Apiresponse> handleapiexception(ApiException ex){
        String message = ex.getMessage();
        Apiresponse apiresponse = new Apiresponse(message,false);
        return new ResponseEntity<>(apiresponse, HttpStatus.BAD_REQUEST);
    }


}
