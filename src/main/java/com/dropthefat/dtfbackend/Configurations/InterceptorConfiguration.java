package com.dropthefat.dtfbackend.Configurations;

import com.dropthefat.dtfbackend.Interceptors.AuthenInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenInterceptor());
        
	}
    
}