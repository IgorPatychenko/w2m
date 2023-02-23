package com.example.w2m;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class W2mApplication {

    public static void main(String[] args) {
        SpringApplication.run(W2mApplication.class, args);
    }

}
