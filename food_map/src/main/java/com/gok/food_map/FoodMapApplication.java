package com.gok.food_map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
//@ServletComponentScan
public class FoodMapApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodMapApplication.class, args);
    }

}
