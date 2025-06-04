package com.app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class InternetSettingsSmsApplication {


	public static void main(String[] args) {
		SpringApplication.run(InternetSettingsSmsApplication.class, args);
	}

}
