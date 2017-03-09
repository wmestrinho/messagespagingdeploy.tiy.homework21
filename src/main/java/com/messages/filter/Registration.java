package com.messages.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by WagnerMestrinho on 2/17/17.
 */

@Configuration
public class Registration {
    @Bean
    public FilterRegistrationBean mvcSecurityFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new Authorize());
        registration.addUrlPatterns("/secure/*");
        return registration;
    }

    @Bean
    public FilterRegistrationBean RestFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new RestFilter());
        registration.addUrlPatterns("/rest/*");
        return registration;
    }

}
