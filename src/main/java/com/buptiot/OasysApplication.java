package com.buptiot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@SpringBootApplication
@ComponentScan({"com.buptiot"})
@ServletComponentScan
public class OasysApplication {

	public static void main(String[] args) {
		SpringApplication.run(OasysApplication.class, args);
	}
}
