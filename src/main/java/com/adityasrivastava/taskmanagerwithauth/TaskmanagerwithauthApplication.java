package com.adityasrivastava.taskmanagerwithauth;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TaskmanagerwithauthApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new PasswordEncoder() {
			@Override
			public String encode(CharSequence rawPassword) {
				return null;
			}

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return false;
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(TaskmanagerwithauthApplication.class, args);
	}

}
