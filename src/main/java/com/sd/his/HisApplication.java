package com.sd.his;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HisApplication  {
	private final Logger logger = LoggerFactory.getLogger(HisApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(HisApplication.class, args);
	}


}
