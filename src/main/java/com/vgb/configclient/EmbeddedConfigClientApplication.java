package com.vgb.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@EnableConfigServer
@SpringBootApplication
@RestController
public class EmbeddedConfigClientApplication {

    @Value("${props.name}")
    private String name;
    @Value("${props.greeting}")
    private String greeting;


//    @Value("${custom.prop.1}")
//    private String p1;

    public static void main(String[] args) {
		SpringApplication.run(EmbeddedConfigClientApplication.class, args);
	}

    @GetMapping("/props")
    public Map<String, String> applicationProperties() {
        return new HashMap() {{
            put("name", name);
            put("greeting", greeting);

        }};
    }
}
