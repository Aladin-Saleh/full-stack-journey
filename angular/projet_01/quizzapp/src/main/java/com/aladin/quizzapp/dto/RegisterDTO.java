package com.aladin.quizzapp.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RegisterDTO {

    private String username;
    
    private String email;
    
    private String password;

    private Boolean isTeacher;
    
}
