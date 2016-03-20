package com.weijuly.develop.ras.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class TransportInterceptor implements HandlerInterceptor {

	final Logger logger = LoggerFactory.getLogger(TransportInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object data, Exception ex)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object data, ModelAndView model)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {

		logger.info("{} {} {}", request.getRemoteHost(), request.getMethod(),
				request.getRequestURI());
		return true;
	}

}
