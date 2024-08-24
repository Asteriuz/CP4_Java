package br.com.fiap.cp4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class cp4Application {

	public static void main(String[] args) {
		SpringApplication.run(cp4Application.class, args);
	}

}
