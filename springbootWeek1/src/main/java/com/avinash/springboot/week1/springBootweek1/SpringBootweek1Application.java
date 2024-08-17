package com.avinash.springboot.week1.springBootweek1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootweek1Application implements CommandLineRunner {

	@Autowired
	Apple obj;

	public static void main(String[] args) {

		SpringApplication.run(SpringBootweek1Application.class, args);
	}

@Override
	public void run(String... args) throws Exception{
		obj.taste();
	}

}


