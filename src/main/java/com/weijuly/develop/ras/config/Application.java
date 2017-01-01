package com.weijuly.develop.ras.config;

import com.weijuly.develop.ras.impl.*;
import com.weijuly.develop.ras.validator.InputValidator;
import com.weijuly.develop.ras.xform.DataTransformer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication

@EntityScan("com.weijuly.develop.ras.persist")
@EnableJpaRepositories("com.weijuly.develop.ras.persist")
public class Application extends WebMvcConfigurerAdapter {

	public static void main(String args[]) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Admin admin() {
		return new AdminImpl();
	}

	@Bean
	public Simulator simulator() {
		return new SimulatorImpl();
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

	@Bean
	public InputValidator validate() {
		return new InputValidator();
	}

	@Bean
	public DataTransformer transformer() {
		return new DataTransformer();
	}

}
