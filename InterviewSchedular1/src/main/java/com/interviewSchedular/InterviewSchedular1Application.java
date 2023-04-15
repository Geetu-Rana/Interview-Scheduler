package com.interviewSchedular;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Interview Schedular API",
version = "1.1"),
servers = {
		@Server(url = "/", description = "Default Server URL") }

)
public class InterviewSchedular1Application {

	public static void main(String[] args) {
		SpringApplication.run(InterviewSchedular1Application.class, args);
	}

}
