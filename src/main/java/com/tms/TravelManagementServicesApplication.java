package com.tms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TravelManagementServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelManagementServicesApplication.class, args);
	}

}
