package com.gc.ims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class ImsApplication {

    public static void main(String[] args) {
        System.out.println("service start");
        SpringApplication.run(ImsApplication.class, args);
    }

}
