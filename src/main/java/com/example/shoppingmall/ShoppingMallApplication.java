package com.example.shoppingmall;

import com.example.shoppingmall.item.properties.StorageProperties;
import com.example.shoppingmall.item.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShoppingMallApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingMallApplication.class, args);
	}

}
