package com.weijuly.develop.ras.xform;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.weijuly.develop.ras.config.Application;
import com.weijuly.develop.ras.data.HTTPRequest;
import com.weijuly.develop.ras.data.HTTPResponse;
import com.weijuly.develop.ras.data.InOutConfiguration;
import com.weijuly.develop.ras.persist.InOutConfigurationDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
@WebIntegrationTest
public class DataTransformerTest {

	@Autowired
	DataTransformer xformer;

	@Autowired
	ObjectMapper mapper;

	String name = "Kim Tae-Yeon";

	@Test(expected = NullPointerException.class)
	public void transformShouldThrowNullPointerExceptionWhenPassedNull() throws Exception {
		InOutConfiguration configuration = null;
		xformer.xform(configuration);
	}

	@Test
	public void transformShouldPopulateNullStrings() throws Exception {
		InOutConfigurationDO configurationDO = xformer.xform(new InOutConfiguration(null,name, null, null));
		assertNotNull(configurationDO);
		assertEquals("null", configurationDO.getRequest());
		assertEquals("null", configurationDO.getResponse());
		assertEquals(name, configurationDO.getName());
	}

	@Test
	public void transformShouldTransform() throws Exception {
		InOutConfigurationDO configurationDO = xformer.xform(new InOutConfiguration(null,name, request(), response()));
		assertNotNull(configurationDO);
		assertEquals(name, configurationDO.getName());
		assertNotNull(configurationDO.getRequest());
		assertNotNull(configurationDO.getResponse());
	}

	@Test(expected = NullPointerException.class)
	public void xformShouldThrowNullPointerExceptionWhenPassedNull() throws Exception {
		InOutConfigurationDO configurationDO = null;
		xformer.xform(configurationDO);
	}

	@Test(expected = NullPointerException.class)
	public void xformShouldThrowNullPointerExceptionWhenElementsAreNull() throws Exception {
		xformer.xform(new InOutConfigurationDO(name, null, null));
	}

	@Test
	public void xformShouldTransform() throws Exception {
		InOutConfiguration configuration = xformer.xform(new InOutConfigurationDO(name,
				mapper.writeValueAsString(request()), mapper.writeValueAsString(response())));
		assertNotNull(configuration);
		assertEquals(name, configuration.getName());
		assertNotNull(configuration.getRequest());
		assertNotNull(configuration.getResponse());
	}

	private HTTPResponse response() {
		return new HTTPResponse(200, headers(), body());
	}

	private HTTPRequest request() {
		return new HTTPRequest("GET", "/v1/korea/seoul", headers(), body());
	}

	private Map<String, String> headers() {
		Map<String, String> headers = new HashMap<>();
		headers.put("Accept", "application/json");
		headers.put("Reject", "application/xml");
		return headers;
	}

	private String body() {
		return "{ Kim Tae-Yeon sings songs soulfully }";
	}

}
