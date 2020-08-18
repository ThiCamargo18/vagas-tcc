package com.example.mac.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerInterceptor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MyException.class)
    public final ResponseEntity<Object> messageInvalid(MyException message){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message.getMessage());
    }
}
