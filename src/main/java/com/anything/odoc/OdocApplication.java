package com.anything.odoc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.anything.odoc")
public class OdocApplication {

    public static void main(String[] args) {
        SpringApplication.run(OdocApplication.class, args);
    }

}
