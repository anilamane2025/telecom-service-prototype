package com.telecom.tsms.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI telecomServiceOpenAPI(){

        return new OpenAPI()
                .info(new Info()
                        .title("Telecom Service Management System API")
                        .description("Spring Boot REST APIs for Telecom Service Management")
                        .version("1.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("Project Documentation"));

    }

}
