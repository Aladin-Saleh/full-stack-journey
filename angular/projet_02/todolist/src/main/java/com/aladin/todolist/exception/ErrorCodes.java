package com.aladin.todolist.exception;

public enum ErrorCodes {
   
    USER_NOT_VALID(1000),
    USER_NOT_FOUND(1001),
    
    JWT_NOT_FOUND(2000),
    JWT_NOT_VALID(2001),

    TASK_NOT_VALID(3000),
    TASK_NOT_FOUND(3001),

    TODOLIST_NOT_VALID(4000),
    TODOLIST_NOT_FOUND(4001);
    

    private int codeError;

    ErrorCodes(int codeError) {
        this.codeError = codeError;
    }

    public int getCodeError() {
        return codeError;
    }
}
