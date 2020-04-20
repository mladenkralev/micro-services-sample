package com.crafters.ratingsrecipeservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RatingsRecipeServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(RatingsRecipeServiceApplication.class, args);
	}
}

