package com.example.alospringdata.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class FilterConfiguration {
// DEIXE COMENTADO PARA TESTAR SEM AUTENTICAÇÃO
//    @Bean
//    public FilterRegistrationBean<CorsFilter> corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
//        config.addAllowedMethod("PATCH");
//        config.addAllowedMethod("DELETE");
//        config.addAllowedMethod("POST");
//        config.addAllowedMethod("GET");
//        source.registerCorsConfiguration("/**", config);
//        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
//        bean.setOrder(0);
//        return bean;
//    }

    @Bean
    public FilterRegistrationBean<AccessFilter> registrationBean(){ // aplica o access filter
        FilterRegistrationBean<AccessFilter> register = new FilterRegistrationBean<>();
        register.setFilter(new AccessFilter());
        register.addUrlPatterns("/apis/autor/*");
        //register.addUrlPatterns("/api/cidadao/*");
        register.setOrder(1);
        return register;
    }
}

