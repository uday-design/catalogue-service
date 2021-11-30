package com.sapient.bp.catalogue.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomBeans {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
