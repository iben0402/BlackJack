package com.iwonabendig.blackjack.exception;

import com.iwonabendig.blackjack.model.dto.ErrorResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(GameNotInitializedException.class)
    public ResponseEntity<ErrorResponseDTO> handleGameNotInitialized(GameNotInitializedException ex) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponseDTO(400, ex.getMessage(), LocalDateTime.now().toString()));
    }

    @ExceptionHandler(GameAlreadyOverException.class)
    public ResponseEntity<ErrorResponseDTO> handleGameAlreadyOver(GameAlreadyOverException ex) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponseDTO(400, ex.getMessage(), LocalDateTime.now().toString()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(Exception ex) {
        log.error("Unexpected error occurred", ex);
        return ResponseEntity.internalServerError()
                .body(new ErrorResponseDTO(500, ex.getMessage(), LocalDateTime.now().toString()));
    }
}