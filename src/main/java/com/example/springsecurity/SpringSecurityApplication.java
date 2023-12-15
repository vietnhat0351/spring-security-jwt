package com.example.springsecurity;

import com.example.springsecurity.entities.Product;
import com.example.springsecurity.entities.Role;
import com.example.springsecurity.entities.User;
import com.example.springsecurity.repositories.ProductRepository;
import com.example.springsecurity.repositories.UserRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

//	@Bean
	CommandLineRunner runner(ProductRepository repository, UserRepository userRepository) {
		return args -> {
			Faker faker = new Faker();
			Product product = new Product();
			for (int i = 1; i <= 20; i++) {
				String productName = faker.commerce().productName();
				String productCategory = faker.commerce().department();
				String productMaterial = faker.commerce().material();
				double productPrice = faker.number().randomDouble(2, 10, 1000);
				product = Product.builder()
						.name(productName)
						.category(productCategory)
						.material(productMaterial)
						.price(productPrice).build();
				repository.save(product);
			}
		};
	}
}
