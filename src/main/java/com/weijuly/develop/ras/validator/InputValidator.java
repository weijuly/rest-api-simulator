package com.weijuly.develop.ras.validator;

import com.weijuly.develop.ras.data.InOutConfiguration;
import com.weijuly.develop.ras.impl.ConfigException;

public class InputValidator {

	public  static void validate(final InOutConfiguration request) throws ConfigException {
		if (request == null) {
			throw new ConfigException("request is null");
		}
		if (request.getName() == null || request.getName().isEmpty()) {
			throw new ConfigException("request name is null or empty ");
		}
		HTTPRequestValidator.validate(request.getRequest());
		HTTPResponseValidator.validate(request.getResponse());
	}

}
