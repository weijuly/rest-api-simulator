package com.weijuly.develop.ras.validator;

import org.junit.Test;

import com.weijuly.develop.ras.data.HTTPRequest;
import com.weijuly.develop.ras.impl.ConfigException;

public class HTTPRequestValidatorTest {

	@Test(expected = ConfigException.class)
	public void shouldThrowExceptionWhileNull() throws Exception {
		HTTPRequestValidator.validate(null);
	}

	@Test(expected = ConfigException.class)
	public void shouldThrowExceptionWhenMethodIsNull() throws Exception {
		HTTPRequest request = new HTTPRequest(null, null, null, null);
		HTTPRequestValidator.validate(request);
	}

	@Test(expected = ConfigException.class)
	public void shouldThrowExceptionWhenMethodIsInvalid() throws Exception {
		HTTPRequest request = new HTTPRequest("FREE", null, null, null);
		HTTPRequestValidator.validate(request);
	}

	@Test(expected = ConfigException.class)
	public void shouldThrowExceptionWhenPathIsNull() throws Exception {
		HTTPRequest request = new HTTPRequest("GET", null, null, null);
		HTTPRequestValidator.validate(request);
	}

	@Test(expected = ConfigException.class)
	public void shouldThrowExceptionWhenPathIsEmpty() throws Exception {
		HTTPRequest request = new HTTPRequest("GET", "", null, null);
		HTTPRequestValidator.validate(request);
	}

	@Test
	public void shouldNotThrowWhenArgsOk() throws Exception {
		HTTPRequest request = new HTTPRequest("GET", "/india", null, null);
		HTTPRequestValidator.validate(request);
	}
}
