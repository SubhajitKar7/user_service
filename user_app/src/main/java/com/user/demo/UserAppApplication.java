package com.user.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.user.demo.filter.JWTFilter;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition
public class UserAppApplication {

	@Bean
	public FilterRegistrationBean jwtFilter()
	{
	 FilterRegistrationBean fb = new FilterRegistrationBean();
	 fb.setFilter(new JWTFilter());
	 fb.addUrlPatterns("/api/v1.0/*");
	
	 return fb;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(UserAppApplication.class, args);
	}

}
