package com.hui.config;

import org.springframework.context.annotation.Configuration;

/* 3-1-1 注入IRule的实现类(Bean)来选择负债均衡规则 */
/**
 * Ribbon(负债均衡工具)配置类, 选择不同的负债均衡策略
 */
@Configuration
public class RibbonConfig {
//    @Bean
//    public IRule setIRule(){
//        /* 使用随机负载均衡规则 */
//        return new RandomRule();
//    }
}
