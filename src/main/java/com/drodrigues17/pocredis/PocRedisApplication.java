package com.drodrigues17.pocredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class PocRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocRedisApplication.class, args);
	}

}
