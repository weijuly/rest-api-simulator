package com.weijuly.develop.ras.impl;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.weijuly.develop.ras.data.InOutConfiguration;

@Aspect
public class AdminTransportLogger {

	final Logger logger = LoggerFactory.getLogger(AdminTransportLogger.class);

	@Autowired
	ObjectMapper mapper;

	@Pointcut("execution(* com.weijuly.develop.ras.impl.AdminImpl.create(com.weijuly.develop.ras.data.InOutConfiguration)) && args(config)")
	public void create(InOutConfiguration config) {
	}

	@Around("create(config)")
	public Object logTransport(ProceedingJoinPoint point,
							   InOutConfiguration config) throws Throwable {
		try {
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			logger.info("request body: {}", mapper.writeValueAsString(config));
			Object result = point.proceed();
			logger.info("response body: {}", mapper.writeValueAsString(result));
			return result;
		} catch (Throwable ex) {
			logger.info("Demanding refund !");
			throw ex;
		}
	}
}
