package com.weijuly.develop.ras.config;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.weijuly.develop.ras.impl.Admin;
import com.weijuly.develop.ras.impl.AdminImpl;
import com.weijuly.develop.ras.impl.AdminTransportLogger;
import com.weijuly.develop.ras.persist.InOutConfigurationDAO;
import com.weijuly.develop.ras.persist.InOutConfigurationDAOImpl;

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
	
	
	
	

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			DataSource source, JpaVendorAdapter adapter) {
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(source);
		bean.setJpaVendorAdapter(adapter);
		bean.setPackagesToScan("com.weijuly.develop.ras.persist");
		return bean;
	}

	@Bean
	public JpaVendorAdapter adapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.HSQL);
		adapter.setShowSql(true);
		adapter.setGenerateDdl(false);
		adapter.setDatabasePlatform("org.hibernate.dialect.HSQLDialect");
		return adapter;
	}

	@Bean
	public PersistenceAnnotationBeanPostProcessor paPostProcessor() {
		return new PersistenceAnnotationBeanPostProcessor();
	}
	
	@Bean
	public InOutConfigurationDAO dao(){
		return new InOutConfigurationDAOImpl();
	}

}
