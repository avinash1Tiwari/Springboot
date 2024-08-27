package com.avinash.springboot.week1.springBootweek1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
//            factory method
    Apple getApple(){
        return new Apple();
    }
}
