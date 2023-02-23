package com.example.w2m.controlleradvicers;

import com.example.w2m.exceptions.SuperHeroNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SuperHeroExceptionHandler {

    @ExceptionHandler(SuperHeroNotFoundException.class)
    public ResponseEntity<Object> handleSuperHeroNotFound() {
        return ResponseEntity.notFound().build();
    }

    // More exception handlers for other errors ...

}
