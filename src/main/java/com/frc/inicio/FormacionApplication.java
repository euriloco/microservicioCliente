package com.frc.inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = {"com.frc.controller", "com.frc.service"})
public class FormacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(FormacionApplication.class, args);
		
		
	}
	@Bean
		public RestTemplate template() {
			return new RestTemplate();
		}

}
