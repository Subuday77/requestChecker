package com.requestChecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class RequestCheckerApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(RequestCheckerApplication.class, args);
		System.out.println("Started............");
	}

}
