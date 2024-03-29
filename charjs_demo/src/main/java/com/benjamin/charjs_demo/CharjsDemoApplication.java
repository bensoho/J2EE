package com.benjamin.charjs_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.benjamin.charjs_demo.repository.dao")
public class CharjsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CharjsDemoApplication.class, args);
    }

}
