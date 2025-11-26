package com.arlen.ProjectOn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProjectOnApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectOnApplication.class, args);
	}

}
