package com.petadoption;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class PetAdoptionServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetAdoptionServerApplication.class, args);
    }

}
