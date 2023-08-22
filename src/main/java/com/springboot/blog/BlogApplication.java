package com.springboot.blog;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Spring Boot Blog App REST APIs",
                description = "Spring Boot Blog App REST APIs Documentation",
                version = "v1.0",
                contact = @Contact(
                        name = "John Kanellopoulos",
                        email = "johnnykanello@gmail.com",
                        url = "https://github.com/John-Kanello/Blog-Application.git"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://github.com/John-Kanello/Blog-Application.git"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Spring Boot Blog Application Documentation",
                url = "https://github.com/John-Kanello/Blog-Application.git"
        )
)
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
}