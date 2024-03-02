package com.nsharma.springboot;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot REST API Documentation",
				description = "Spring Boot REST API Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Neeraj Sharma",
						email = "nsharma0619@gmail.com",
						url = "https://nsharma0619.github.io"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://nsharma0619.github.io"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring boot user management Documentation",
				url = "https://www.javaguides.net/user_management.html"
		)
)
public class SpringbootRestApiApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestApiApplication.class, args);
	}

}
