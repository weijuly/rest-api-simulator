package com.weijuly.develop.ras.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weijuly.develop.ras.data.HTTPRequest;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.servlet.HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE;
public class SimulatorImpl implements Simulator {

	@Autowired
	ObjectMapper mapper;


	final private Logger logger = LoggerFactory.getLogger(AdminImpl.class);

	@Override
	public ResponseEntity<String> simulate(HttpServletRequest httpServletRequest) {
		try {
			HTTPRequest request = xform(httpServletRequest);
			logger.info("simulate request:{}", mapper.writeValueAsString(request));

		} catch (IOException ex) {

		}
		return ResponseEntity.status(OK).body("Weibo");
	}

	private HTTPRequest xform(HttpServletRequest httpServletRequest) throws IOException {

		HTTPRequest request = new HTTPRequest(httpServletRequest.getMethod(),
				(String) httpServletRequest.getAttribute(PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE),
				Collections.list(httpServletRequest.getHeaderNames()).stream().collect(Collectors.toMap(x -> x, x -> httpServletRequest.getHeader(x))),
				IOUtils.toString(httpServletRequest.getInputStream(), "UTF-8"));
		return request;
	}
}
