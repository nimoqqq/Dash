package com.example.mystarter_test;

import org.javaboy.HelloService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MystarterTestApplicationTests {

    @Autowired
    private HelloService helloService;


    @Test
    void contextLoads() {
        System.out.println(helloService.sayHello());
    }

}
