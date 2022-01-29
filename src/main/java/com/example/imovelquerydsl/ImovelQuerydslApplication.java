package com.example.imovelquerydsl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ImovelQuerydslApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImovelQuerydslApplication.class, args);
	}

}
