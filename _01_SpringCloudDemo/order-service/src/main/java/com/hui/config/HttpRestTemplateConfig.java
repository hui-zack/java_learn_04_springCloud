package com.hui.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/* 1-1 创建RestTemplate配置类, 注入RestTemplate对象 */
@Configuration
@ConfigurationProperties(prefix = "http.client")
@Data
public class HttpRestTemplateConfig {
    /**
     * 连接超时时间, 当超出时间无法连接到url抛出SocketException: connect time out异常
     */
    private Integer connectTimeout = 2000;

    /**
     * 读取超时时间, 当超出时间无法读取到内容将抛出SocketException: read time out异常
     */
    private Integer readTimeout = 2000;

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        /* 设置连接超时时间 */
        requestFactory.setConnectTimeout(connectTimeout);
        /* 设置请求读取超时时间 */
        requestFactory.setReadTimeout(readTimeout);

        RestTemplate restTemplate = new RestTemplate(requestFactory);
        /* 设置响应数据解码方式为UTF8 */
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        return restTemplate;
    }
}
