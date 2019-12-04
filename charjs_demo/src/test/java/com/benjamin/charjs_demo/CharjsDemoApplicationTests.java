package com.benjamin.charjs_demo;

import com.benjamin.charjs_demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CharjsDemoApplicationTests {


    @Autowired
    UserService service;
    @Test
    void contextLoads() {
        System.out.println();


    }

}
