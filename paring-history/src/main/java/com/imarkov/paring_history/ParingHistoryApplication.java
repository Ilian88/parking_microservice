package com.imarkov.paring_history;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories
public class ParingHistoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParingHistoryApplication.class, args);
	}
}
