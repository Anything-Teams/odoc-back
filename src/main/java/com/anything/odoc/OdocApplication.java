package com.anything.odoc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.anything.odoc")
@EnableScheduling
public class OdocApplication {

    public static void main(String[] args) {
        SpringApplication.run(OdocApplication.class, args);
    }

}
