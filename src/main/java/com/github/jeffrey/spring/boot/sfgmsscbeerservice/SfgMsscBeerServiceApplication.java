package com.github.jeffrey.spring.boot.sfgmsscbeerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisAutoConfiguration;

@SpringBootApplication(exclude = ArtemisAutoConfiguration.class)
public class SfgMsscBeerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SfgMsscBeerServiceApplication.class, args);
	}

}
