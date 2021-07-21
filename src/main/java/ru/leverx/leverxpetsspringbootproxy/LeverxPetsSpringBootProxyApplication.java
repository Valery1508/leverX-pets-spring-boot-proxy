package ru.leverx.leverxpetsspringbootproxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.sap.cloud.sdk"})
@ServletComponentScan({"com.sap.cloud.sdk"})
public class LeverxPetsSpringBootProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeverxPetsSpringBootProxyApplication.class, args);
    }

}
