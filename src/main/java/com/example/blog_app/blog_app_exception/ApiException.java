package com.example.blog_app.blog_app_exception;

public class ApiException extends RuntimeException{

    public ApiException(String message) {
        super(message);
    }

    public ApiException(){
        super();
    }

}
