package hu.nye.webprojekt.HomeWork.controller;

import hu.nye.webprojekt.HomeWork.exception.BookNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = BookController.class)
public class BookControllerAdvice {

    @ExceptionHandler(value = BookNotFoundException.class)
    public ResponseEntity<Void> handleBookNotFoundException(){
        return ResponseEntity.status(404).build();
    }
}
