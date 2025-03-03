package com.example.blog_app.blog_app_payloads;

public class Apiresponse {
    private String message;
    private boolean success;

    public Apiresponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public Apiresponse() {
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}
