package com.oceanLife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "OceanLife", version = "1.0.0")) //swagger標題說明
@SpringBootApplication
public class OceanLifeApplication {

	public static void main(String[] args) {
		SpringApplication.run(OceanLifeApplication.class, args);
	}

}
