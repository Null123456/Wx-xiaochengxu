package com.ssk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@MapperScan("com.ssk.mapper")
@SpringBootApplication
public class mainApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(mainApplication.class, args);
    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(mainApplication.class);
    }
}
