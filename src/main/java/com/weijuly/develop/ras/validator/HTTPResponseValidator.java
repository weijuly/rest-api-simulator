package com.weijuly.develop.ras.validator;

import com.weijuly.develop.ras.data.HTTPResponse;
import com.weijuly.develop.ras.impl.ConfigException;

public class HTTPResponseValidator {
	public static void validate(final HTTPResponse response) throws ConfigException {
		if (response == null) {
			throw new ConfigException("response is null");
		}
		if (response.getCode() == null) {
			throw new ConfigException("code is null");
		}
		if (response.getBody() == null) {
			throw new ConfigException("body is null");
		}
	}
}
