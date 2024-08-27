package com.avinash.springbootwebservices.Springboot_webservices_Architecture.Controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
public class Employee1 {

    @GetMapping(path = "/name")
    public String getUserName(@RequestParam String name)
    {
        return "user name : " + name;
    }

    @GetMapping(path = "/{age}")
    public String getUserAge(@PathVariable  int age)
    {
        return "user age : " + age;
    }

    @GetMapping(path = "/hobbie")
    public String getUserHobbie(@RequestParam(required= false) String hobbie)
    {
        return "user name : " + hobbie;
    }


    @GetMapping                                 //as here I don't provide any path, so it take path ony of it's parent i.e => /user
    public String getYourname()
    {
        return " My name is Avinash Tiwari";
    }
}
