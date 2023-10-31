package co.com.michael.password.validator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class PasswordValidatorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PasswordValidatorsApplication.class, args);
	}

}
