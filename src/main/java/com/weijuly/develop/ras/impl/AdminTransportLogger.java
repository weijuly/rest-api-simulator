package com.weijuly.develop.ras.impl;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weijuly.develop.ras.data.InOutConfiguration;

@Aspect
public class AdminTransportLogger {

	final Logger logger = LoggerFactory.getLogger(AdminTransportLogger.class);

	@Autowired
	ObjectMapper mapper; // Auto-wired by framework

	@Pointcut("execution(* com.weijuly.develop.ras.impl.AdminImpl.create(com.weijuly.develop.ras.data.InOutConfiguration)) && args(config)")
	public void create(InOutConfiguration config) {
	}

	@Around("create(config)")
	public Object logTransport(ProceedingJoinPoint point,
			InOutConfiguration config) throws Throwable {
		try {
			logger.info("RequestBody: {}", mapper.writeValueAsString(config));
			Object result = point.proceed();
			logger.info("ResponseBody: {}", mapper.writeValueAsString(result));
			return result;
		} catch (Throwable ex) {
			logger.info("Demanding refund !");
			throw ex;
		}
	}
}
