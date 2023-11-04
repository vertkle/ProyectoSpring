package com.codigo.proyectospringcodigo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProyectoSpringCodigoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProyectoSpringCodigoApplication.class, args);
    }

}
