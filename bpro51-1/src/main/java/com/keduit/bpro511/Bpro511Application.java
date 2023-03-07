package com.keduit.bpro511;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Bpro511Application {

	public static void main(String[] args) {
		SpringApplication.run(Bpro511Application.class, args);
	}

}
