package com.weijuly.develop.ras.validator;

import org.junit.Test;

import com.weijuly.develop.ras.data.HTTPResponse;
import com.weijuly.develop.ras.impl.ConfigException;

public class HTTPResponseValidatorTest {
	
	@Test(expected = ConfigException.class)
	public void shouldThrowExceptionWhenNull() throws Exception {
		HTTPResponseValidator.validate(null);
	}

	@Test(expected = ConfigException.class)
	public void shouldThrowExceptionWhenCodeIsNull() throws Exception {
		HTTPResponse response = new HTTPResponse(null, null, null);
		HTTPResponseValidator.validate(response);
	}

	@Test(expected = ConfigException.class)
	public void shouldThrowExceptionWhenBodyIsNull() throws Exception {
		HTTPResponse response = new HTTPResponse(200, null, null);
		HTTPResponseValidator.validate(response);
	}

	@Test
	public void shouldNotThrowExceptionWhenArgsOk() throws Exception {
		HTTPResponse response = new HTTPResponse(200, null, "--");
		HTTPResponseValidator.validate(response);
	}
}
