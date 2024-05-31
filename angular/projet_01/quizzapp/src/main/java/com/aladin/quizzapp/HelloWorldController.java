package com.aladin.quizzapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Spring MVC, utilise le Spring MVC pour gerer les requete web
public class HelloWorldController {

    @GetMapping("/")
    public String index() {
        return "Hello World !";
    }
    
}
