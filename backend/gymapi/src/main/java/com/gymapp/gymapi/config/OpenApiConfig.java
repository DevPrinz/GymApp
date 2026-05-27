package com.gymapp.gymapi.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
@OpenAPIDefinition(
    info = @Info(title = "GymApp API", version = "v1"),
    security = @SecurityRequirement(name = "bearerAuth")
)
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT",
    description = "Supabase JWT here — get it from /auth/v1/token"
)
public class OpenApiConfig {

    @Bean
    public OpenAPI gymAppOpenAPI() {
        return new OpenAPI()
            .info(new io.swagger.v3.oas.models.info.Info()
                .title("GymApp API")
                .description("REST API for GymApp — sessions, matching, AI coach, nutrition, leaderboards.")
                .version("v1")
                .contact(new io.swagger.v3.oas.models.info.Contact()
                    .name("GymApp")
                    .email("ifethomas18@gmail.com")))
            .servers(List.of(
                new Server().url("http://localhost:8081").description("Local development")));
    }
}
