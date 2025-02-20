package com.example.config;

import com.example.interceptor.JWTTokenAdminInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurationSupport {
    private final JWTTokenAdminInterceptor jwtTokenAdminInterceptor;

    public WebMvcConfiguration(JWTTokenAdminInterceptor jwtTokenAdminInterceptor) {
        this.jwtTokenAdminInterceptor = jwtTokenAdminInterceptor;
    }

    protected void addInterceptors(InterceptorRegistry registry){
        log.info("Start to register custom interceptor");
        registry.addInterceptor(jwtTokenAdminInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login");
    }

}
