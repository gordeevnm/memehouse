package ru.kek.memehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class MemeHouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemeHouseApplication.class, args);
	}
}
