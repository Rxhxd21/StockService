package com.rxhxdproject.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RxappApplication {

	public static void main(String[] args) {
		SpringApplication.run(RxappApplication.class, args);
	}
}
