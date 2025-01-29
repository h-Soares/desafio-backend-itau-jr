package com.soaresdev.itautestjr.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Test for Developer Jr. (Itau)").version("1.0.0")
                        .contact(new Contact()
                                .name("Linkedin")
                                .url("https://www.linkedin.com/in/hiago-soares-96840a271/")));
    }
}