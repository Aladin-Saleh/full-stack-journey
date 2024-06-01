package com.aladin.quizzapp.handlers;

import java.util.ArrayList;
import java.util.List;

import com.aladin.quizzapp.exception.ErrorCodes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDTO {

    private Integer httpCode;
    private ErrorCodes errorCode;
    private String message;

    @Builder.Default
    private List<String> errors = new ArrayList<>();

}
