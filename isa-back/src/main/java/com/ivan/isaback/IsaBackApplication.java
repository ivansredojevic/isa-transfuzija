package com.ivan.isaback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IsaBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsaBackApplication.class, args);
	}

}
