package com.abhinavgarg.microservice1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class AppEntry {
	public static void main(final String[] args) {
		SpringApplication.run(AppEntry.class, args);
	}

}
