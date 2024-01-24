package br.com.grupo63.serviceproduction.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class JwtFilterConfig {

    @Autowired
    private JwtService jwtService;

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilterFilterRegistrationBean() {
        FilterRegistrationBean<JwtFilter> jwtFilterFilterRegistrationBean = new FilterRegistrationBean<>();
        jwtFilterFilterRegistrationBean.setFilter(new JwtFilter(jwtService));
        jwtFilterFilterRegistrationBean.setUrlPatterns(Arrays.asList("/orders", "/payments/initialize"));
        return jwtFilterFilterRegistrationBean;
    }
}
