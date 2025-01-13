package com.main.utils;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

        @Bean
        public OpenAPI customOpenAPI() {
                return new OpenAPI()
                                .info(new Info()
                                                .title("API Estrella")
                                                .version("1.0")
                                                .description("Documentación de la API Estrella")
                                                .contact(new Contact()
                                                                .name("Etrellados")
                                                                .url("https://Etrellados.com")));
        }
}
