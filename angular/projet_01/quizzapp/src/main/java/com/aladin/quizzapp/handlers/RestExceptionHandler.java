package com.aladin.quizzapp.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.aladin.quizzapp.exception.EntityNotFoundException;
import com.aladin.quizzapp.exception.InvalidEntityException;




@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleException(EntityNotFoundException exception, WebRequest request) {

        final HttpStatus notFound = HttpStatus.NOT_FOUND;

        final ErrorDTO errorDto = ErrorDTO.builder() 
            .errorCode(exception.getErrorCode())
            .httpCode(notFound.value())
            .message(exception.getMessage())
            .build(); 

        return new ResponseEntity<ErrorDTO>(errorDto, notFound);

    }

    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<ErrorDTO> handleException(InvalidEntityException exception, WebRequest request) {

        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        final ErrorDTO errorDto = ErrorDTO.builder()
            .errorCode(exception.getErrorCode())
            .httpCode(badRequest.value())
            .message(exception.getMessage())
            .errors(exception.getErrors())
            .build();

        return new ResponseEntity<ErrorDTO>(errorDto, badRequest);
    }


}
