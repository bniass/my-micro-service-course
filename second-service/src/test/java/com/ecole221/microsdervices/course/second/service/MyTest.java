package com.ecole221.microsdervices.course.second.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyTest {
    @Test
    void contextLoads() {
    }

    @Test
    void sample(){
        Assertions.assertEquals(1, 1);
    }
}
