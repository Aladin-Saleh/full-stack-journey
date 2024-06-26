package com.aladin.quizzapp.exception;

public enum ErrorCodes {




    QUIZZ_NOT_FOUND(1000),
    QUIZZ_NOT_VALID(1001),
    QUESTION_NOT_FOUND(2000),
    QUESTION_NOT_VALID(2001),
    CHOICE_NOT_FOUND(3000),
    CHOICE_NOT_VALID(3001),
    STUDENT_NOT_FOUND(4000),
    TEACHER_NOT_FOUND(5000),
    TEACHER_NOT_VALID(5001),
    PARTICIPATION_NOT_FOUND(6000),
    PARTICIPATION_NOT_VALID(6001),
    USER_NOT_FOUND(7000),
    USER_NOT_VALID(7001),
    JWT_NOT_VALID(8000),
    JWT_NOT_FOUND(8001);



    private int codeError;

    ErrorCodes(int codeError) {
        this.codeError = codeError;
    }

    public int getCodeError() {
        return codeError;
    }

















}
