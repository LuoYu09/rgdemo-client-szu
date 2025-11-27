package com.szu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.szu.mapper")
public class RgdemoClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(RgdemoClientApplication.class, args);
    }

}
