package com.hladchenko.springrestapi.advice;

import com.hladchenko.springrestapi.excetion.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<String> handleUserNotFoundException() {
        return ResponseEntity.badRequest().body("User Not Found!");
    }

}
