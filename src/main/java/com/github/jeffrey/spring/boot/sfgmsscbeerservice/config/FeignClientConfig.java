package com.github.jeffrey.spring.boot.sfgmsscbeerservice.config;

import feign.auth.BasicAuthRequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jeffreymzd on 10/3/20
 */
@Slf4j
@Configuration
public class FeignClientConfig {
    @Bean
    public BasicAuthRequestInterceptor basicAuthenticationInterceptor(@Value("${sfg.brewery.inventory-user}") String inventoryUser,
                                                                      @Value("${sfg.brewery.inventory-password}") String inventoryPassword) {
        log.info("inventory-user={}, inventory-password={}", inventoryUser, inventoryPassword);
        return new BasicAuthRequestInterceptor(inventoryUser, inventoryPassword);
    }
}
