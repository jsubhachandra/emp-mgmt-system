package com.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author subhachandra
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = { "org.bitbucket.tek.nik.simplifiedswagger", "com.employee" })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
