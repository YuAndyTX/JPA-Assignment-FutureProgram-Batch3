package com.FutureBatch3.JPAAssignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JpaAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaAssignmentApplication.class, args);
	}
}
