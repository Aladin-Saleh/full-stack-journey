package com.aladin.quizzapp.exception;

public enum ErrorCodes {




    QUIZZ_NOT_FOUND(1000),
    QUESTION_NOT_FOUND(2000),
    CHOICE_NOT_FOUND(3000),
    STUDENT_NOT_FOUND(4000),
    TEACHER_NOT_FOUND(5000),
    PARTICIPATION_NOT_FOUND(6000);


    private int codeError;

    ErrorCodes(int codeError) {
        this.codeError = codeError;
    }

    public int getCodeError() {
        return codeError;
    }

















}
