package com.weijuly.develop.ras.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.weijuly.develop.ras.data.InOutConfiguration;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@Aspect
public class AdminTransportLogger {

	final Logger logger = LoggerFactory.getLogger(AdminTransportLogger.class);

	@Autowired
	ObjectMapper mapper;

	@Pointcut("execution(* com.weijuly.develop.ras.impl.AdminImpl.create(com.weijuly.develop.ras.data.InOutConfiguration)) && args(config)")
	public void create(InOutConfiguration config) {
	}

	@Around("create(config)")
	public Object logTransport(ProceedingJoinPoint point, InOutConfiguration config) throws Throwable {
		try {
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			log(config);
			ResponseEntity entity = (ResponseEntity) point.proceed();
			log(entity);
			return entity;
		} catch (Throwable ex) {
			logger.info("Demanding refund !");
			throw ex;
		}
	}

	private void log(InOutConfiguration configuration) throws JsonProcessingException {
		logger.info("\n>>>> create configuration request >>>>\n{}\n<<<< create configuration request <<<<",
				mapper.writeValueAsString(configuration));
	}

	private void log(ResponseEntity entity) {
		logger.info("\n>>>> create configuration response >>>>\nhttp status code: {}\nheaders: {}\nbody: {}\n" +
						"<<<< create configuration response <<<<", entity.getStatusCode(), entity.getHeaders(),
				entity.getBody());
	}
}
