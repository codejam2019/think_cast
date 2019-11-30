package com.code.jam.wather.weatherrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(
        basePackages = "com.code.jam.wather.weatherrest.repositories"
)
public class WeatherRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherRestApplication.class, args);
    }

}
