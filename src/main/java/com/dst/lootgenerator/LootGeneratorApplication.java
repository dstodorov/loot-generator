package com.dst.lootgenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class LootGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(LootGeneratorApplication.class, args);
	}
}
