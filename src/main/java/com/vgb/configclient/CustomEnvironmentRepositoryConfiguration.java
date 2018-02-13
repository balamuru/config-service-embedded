package com.vgb.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.logging.Logger;


@Configuration
@Profile("custom")
public class CustomEnvironmentRepositoryConfiguration {
    final Logger log = Logger.getLogger(this.getClass().getName());


    @Value("${spring.cloud.config.server.custom.userId}")
    private String userId;

    @Value("${spring.cloud.config.server.custom.password}")
    private String password;

    @Value("${spring.cloud.config.server.custom.url}")
    private String url;

    @Value("${spring.cloud.config.server.custom.order:0x7fffffff}")
    private int order;

    @Bean
    @ConditionalOnMissingBean(CustomEnvironmentRepository.class)
    CustomEnvironmentRepository customEnvironmentRepository() {

        CustomEnvironmentRepository repo = new CustomEnvironmentRepository();
        repo.setUrl(url);
        repo.setUserId(userId);
        repo.setPassword(password);
        repo.setOrder(order);
        log.info("################################ " + repo);
        return repo;
    }
}
