package com.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.shop.repository")
@EntityScan("com.shop.domain")
public class ShopServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopServerApplication.class, args);
	}

}
