package com.tekabs.absence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class AbsenceApplication {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(AbsenceApplication.class, args);
    }

}