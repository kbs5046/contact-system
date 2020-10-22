package com.contact.en.syst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ContactSystApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactSystApplication.class, args);
	}

}
