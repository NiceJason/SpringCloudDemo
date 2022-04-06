package com.nicebin.common;

import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ThreadLocalRandom;

@SpringBootTest
class CommonApplicationTests {

    @Test
    void contextLoads() {
        ThreadLocalRandom.current().doubles();
    }

}
