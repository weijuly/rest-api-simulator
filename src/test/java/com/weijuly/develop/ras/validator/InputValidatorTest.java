package com.weijuly.develop.ras.validator;

import org.junit.Test;

import com.weijuly.develop.ras.data.HTTPRequest;
import com.weijuly.develop.ras.data.HTTPResponse;
import com.weijuly.develop.ras.data.InOutConfiguration;
import com.weijuly.develop.ras.impl.ConfigException;

public class InputValidatorTest {

	@Test(expected = ConfigException.class)
	public void shouldThrowExceptionForNull() throws Exception {
		InputValidator.validate(null);
	}

	@Test(expected = ConfigException.class)
	public void shouldThrowExceptionForNullName() throws Exception {
		InOutConfiguration config = new InOutConfiguration(null, null, null);
		InputValidator.validate(config);
	}

	@Test(expected = ConfigException.class)
	public void shouldThrowExceptionForEmptyName() throws Exception {
		InOutConfiguration config = new InOutConfiguration("", null, null);
		InputValidator.validate(config);
	}

	@Test(expected = ConfigException.class)
	public void shouldThrowExceptionForNullRequest() throws Exception {
		InOutConfiguration config = new InOutConfiguration("sample", null, null);
		InputValidator.validate(config);
	}

	@Test(expected = ConfigException.class)
	public void shouldThrowExceptionForNullResponse() throws Exception {
		HTTPRequest request = new HTTPRequest("GET", "/india", null, null);
		InOutConfiguration config = new InOutConfiguration("sample", request, null);
		InputValidator.validate(config);
	}

	@Test
	public void shouldNotThrowWhenArgsOk() throws Exception {
		HTTPRequest request = new HTTPRequest("GET", "/india", null, null);
		HTTPResponse response = new HTTPResponse(200, null, "--");
		InOutConfiguration config = new InOutConfiguration("sample", request, response);
		InputValidator.validate(config);
	}
}
