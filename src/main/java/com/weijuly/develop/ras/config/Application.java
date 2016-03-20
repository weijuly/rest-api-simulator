package com.weijuly.develop.ras.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.weijuly.develop.ras.impl.Admin;
import com.weijuly.develop.ras.impl.AdminImpl;
import com.weijuly.develop.ras.impl.AdminTransportLogger;

@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter {

	public static void main(String args[]) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Admin admin() {
		return new AdminImpl();
	}

	@Bean
	public AdminTransportLogger adminLogger() {
		return new AdminTransportLogger();
	}

	@Bean
	public TransportInterceptor interceptor() {
		return new TransportInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor());
	}
}
