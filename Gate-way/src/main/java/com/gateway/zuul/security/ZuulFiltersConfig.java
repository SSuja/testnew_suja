package com.gateway.zuul.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulFiltersConfig {
    @Bean
    public RequestFilter requestFilter() {
        return new RequestFilter();
    }
}