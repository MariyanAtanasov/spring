package com.example.demo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.demo")
@EntityScan(basePackages = {"com.example.demo.entities"})
@EnableJpaRepositories(basePackages = {"com.example.demo.repository"})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
