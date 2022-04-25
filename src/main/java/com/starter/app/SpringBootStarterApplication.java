package com.starter.app;

import com.starter.app.model.User;
import com.starter.app.model.UserRole;
import com.starter.app.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableMongoRepositories
//@EnableAspectJAutoProxy
public class SpringBootStarterApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringBootStarterApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(UserRepository userRepository) {
		return args -> {
			userRepository.deleteAll();
			userRepository.save( User.builder().
					name("admin").
					username("admin").
					password(encoder().encode("admin")).
					email("admin@gmail.com").
					userRole(UserRole.USER).
					build());
		};
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}
