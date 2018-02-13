package com.vgb.configclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class EmbeddedConfigClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmbeddedConfigClientApplication.class, args);
	}
}
