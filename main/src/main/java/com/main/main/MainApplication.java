package com.main.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.main") // Escanea todo el paquete base
@EntityScan(basePackages = "com.main.modelo.entidades") // Cambia al paquete donde están tus entidades
@EnableJpaRepositories(basePackages = "com.main.modelo.repositorios") // Cambia al paquete donde están los repositorios
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
