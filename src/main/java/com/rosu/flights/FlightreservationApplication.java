package com.rosu.flights;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class FlightreservationApplication {

	public static void main(String[] args) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		
		System.out.println(bCryptPasswordEncoder.encode("pass123"));
		SpringApplication.run(FlightreservationApplication.class, args);
	}

}
