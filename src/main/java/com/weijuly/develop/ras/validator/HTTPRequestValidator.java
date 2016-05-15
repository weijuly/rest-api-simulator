package com.weijuly.develop.ras.validator;

import java.util.ArrayList;
import java.util.List;

import com.weijuly.develop.ras.data.HTTPRequest;
import com.weijuly.develop.ras.impl.ConfigException;

public class HTTPRequestValidator {

	public static void validate(final HTTPRequest request) throws ConfigException {

		List<String> methods = new ArrayList<String>();
		methods.add("GET");
		methods.add("PUT");
		methods.add("POST");
		methods.add("PATCH");
		methods.add("DELETE");
		methods.add("HEAD");

		if (request == null) {
			throw new ConfigException("HTTPRequestValidator.validate: null request");
		}
		if (!methods.contains(request.getMethod())) {
			throw new ConfigException("HTTPRequestValidator.validate: invalid method");
		}
		if (request.getPath() == null || request.getPath().equals("")) {
			throw new ConfigException("HTTPRequestValidator.validate: invalid method");
		}
	}
}
