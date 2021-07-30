package com.example.interceptor.exception;

public class AuthException extends RuntimeException{

    public AuthException(String message){
        super(message);
    }
}
