package com.mapapi.food;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MapapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MapapiApplication.class, args);
    }

}
