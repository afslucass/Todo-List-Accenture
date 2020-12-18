package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.repositories")
@EntityScan("com.example.models")
@ComponentScan("com.example.controllers")
public class RestApiTodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiTodoApplication.class, args);
	}

}
