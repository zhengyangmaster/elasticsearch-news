package com.example.newssearch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.newssearch.mapper")
public class NewsSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsSearchApplication.class, args);
    }

}
