package com.springboot.firstapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootFirstAppApplication {

    @Bean
	public  HelloWorld helloWorld(){
		return new HelloWorld();
	}
	public static void main(String[] args) {
		ApplicationContext applicationContext= SpringApplication.run(SpringBootFirstAppApplication.class, args);
		HelloWorld helloWorld=applicationContext.getBean(HelloWorld.class);
		System.out.println(helloWorld.helloWorld());
	}

}
