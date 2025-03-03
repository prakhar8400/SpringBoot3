package io.reflectoring.SpringBoot3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "io.reflectoring.SpringBoot3")

public class SpringBoot3Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot3Application.class, args);
	}

}
