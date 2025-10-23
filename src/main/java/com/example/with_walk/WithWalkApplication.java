package com.example.with_walk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WithWalkApplication {

	public static void main(String[] args) {
		SpringApplication.run(WithWalkApplication.class, args);
	}

}
