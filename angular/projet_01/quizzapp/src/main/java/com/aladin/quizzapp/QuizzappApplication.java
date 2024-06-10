package com.aladin.quizzapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class QuizzappApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizzappApplication.class, args);

		
	}

}
