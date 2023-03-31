package com.adidas.backend.adiclubservice;

import com.adidas.backend.adiclubservice.infrastructure.driven_adapter.ExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdiClubServiceApplication {

	public static void main(String[] args) {
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
		SpringApplication.run(AdiClubServiceApplication.class, args);
	}

}
