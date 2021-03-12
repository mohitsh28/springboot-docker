package com.poc.spring.docker.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.handler.MappedInterceptor;

@Configuration
public class IncomingRequestInterceptor {

    @Bean
    public MappedInterceptor mappedInterceptor(IncomingRequestInterceptor interceptor) {
        return new MappedInterceptor(new String[]{"/api/**"},(WebRequestInterceptor) interceptor);
    }
}
