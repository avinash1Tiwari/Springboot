package com.avinash.springboot.week1.springBootweek1;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

//@Component => this is used with steriotype method => 1. method


public class Apple {
    String color;
    int weight;

    void taste()
    {
        System.out.println("apples sre sweet in test");
    }

    @PostConstruct
    void callThisBeforeAppleUsed()
    {
        System.out.println("calling this before the apple is created");
    }

    @PreDestroy
    void callBeforeAppleDestroys()
    {
        System.out.println("calling before the apple is destroys");
    }
}
