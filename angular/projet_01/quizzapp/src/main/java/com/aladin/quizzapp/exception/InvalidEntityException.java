package com.aladin.quizzapp.exception;

import java.util.List;

import lombok.Getter;

// Concerne les exceptions qui seront levé lorsque qu'une erreur avec les validators ont lieu (tentative d'insertion de données mal formatée, insertion de données vide, non valide).
@Getter
public class InvalidEntityException extends RuntimeException {

    private ErrorCodes errorCode;
    private List<String> errors;

    public InvalidEntityException(String message) {
        super(message);
    }

    public InvalidEntityException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public InvalidEntityException(String message, Throwable cause, ErrorCodes errorCode) {
        super(message, cause);
        this.errorCode = errorCode;

    }

    public InvalidEntityException(String message, ErrorCodes errorCode) {
        super(message);
        this.errorCode = errorCode;

    }

    public InvalidEntityException(String message, ErrorCodes errorCode, List<String> errors) {
        super(message);
        this.errorCode = errorCode;
        this.errors = errors;

    }
    
    
}
