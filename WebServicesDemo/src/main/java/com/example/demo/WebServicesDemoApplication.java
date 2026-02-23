package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class WebServicesDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebServicesDemoApplication.class, args);
        Database.init();
    }
}
