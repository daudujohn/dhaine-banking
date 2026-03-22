package com.dhaine.banking.service.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Daudu John
 * @createdOn Mar-22(Sun)-2026
 */
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
        .info(
            new Info()
                .title("Dhaine Banking")
                .description(
                    "This documentation contains the APIs exposed for the dhaine banking service")
                .version("1.0"))
        .addServersItem(new Server().description("Local Server").url("http://localhost:8080"))
        .addSecurityItem(new SecurityRequirement().addList("basicAuth"))
        .components(
            new Components()
                .addSecuritySchemes(
                    "basicAuth",
                    new SecurityScheme()
                        .name("basicAuth")
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("basic")));
  }
}
