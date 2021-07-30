package com.example.interceptor.handler;

import com.example.interceptor.exception.AuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
    Interceptor에서 발생하는 예외도 결국엔 DispatcherServlet내에서 발생하는 예외임으로 @RestControllerAdvice에서 ExceptionHandler로 잡을 수 있다.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<String> authException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }
}
