package com.hui.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    /**
     * RestTemplate spring自带的http请求发送对象
     * @return restTemplate请求发送对象
     */

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
