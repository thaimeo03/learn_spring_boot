package com.basic.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.basic.demo.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);

		UserRepository userRepository = context.getBean(UserRepository.class);

		// Fake a user creation
		log.info("Creating a user with username 'testuser' and password 'password123'");

		// userRepository.save(
		// User.builder()
		// .username("testuser")
		// .password("password123")
		// .build());

		log.info("user={}", userRepository.findUserByUsername("testuser"));
	}

}
