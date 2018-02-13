package com.vgb.configclient;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("custom")
public class CustomEnvironmentRepositoryConfiguration {
    @Bean
    @ConditionalOnMissingBean(CustomEnvironmentRepository.class)
    CustomEnvironmentRepository customEnvironmentRepository() {
        return new CustomEnvironmentRepository();
    }
}
