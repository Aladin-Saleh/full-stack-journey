package com.aladin.todolist.exception;

public enum ErrorCodes {
   
    USER_NOT_VALID(1000),
    USER_NOT_FOUND(1001);
    

    private int codeError;

    ErrorCodes(int codeError) {
        this.codeError = codeError;
    }

    public int getCodeError() {
        return codeError;
    }
}
