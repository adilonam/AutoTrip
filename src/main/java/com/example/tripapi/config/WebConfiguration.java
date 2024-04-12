package com.example.tripapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class WebConfiguration implements WebMvcConfigurer  {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Define CORS policy
        registry.addMapping("/**") // Apply to all endpoints
                .allowedOrigins("*") // Allow all origins - be cautious with this in a production environment
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Specify allowed methods
                .allowedHeaders("*") // Allow all headers
                .maxAge(3600); // Specify how long the results of a preflight response can be cached
    }
}
