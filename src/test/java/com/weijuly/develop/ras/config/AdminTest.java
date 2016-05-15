package com.weijuly.develop.ras.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weijuly.develop.ras.data.HTTPRequest;
import com.weijuly.develop.ras.data.HTTPResponse;
import com.weijuly.develop.ras.data.InOutConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.*;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class })
@WebIntegrationTest
public class AdminTest {

	RestTemplate template = new TestRestTemplate();
	HttpHeaders requestHeaders = null;

	@Autowired
	ObjectMapper mapper;

	private final String ADMIN_URL = "http://localhost:8080/admin";

	@Before
	public void setup() {
		requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
	}

	public void adminShouldRespond() {
		ResponseEntity<String> response = template.getForEntity(ADMIN_URL,
				String.class);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		String string = response.getBody();
		assertNotNull(string);
	}

	@Test
	public void shouldFailOnBadRequest() throws JsonProcessingException {
		InOutConfiguration config = null;

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(
				mapper.writeValueAsString(config), requestHeaders);
		ResponseEntity<String> response = template
				.postForEntity(ADMIN_URL, request, String.class);

		Assert.assertEquals(BAD_REQUEST, response.getStatusCode());

	}

	@Test
	public void shouldPersistConfig() throws JsonProcessingException {
		InOutConfiguration config = config();

		System.out.println(mapper.writeValueAsString(config));

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(
				mapper.writeValueAsString(config), requestHeaders);
		ResponseEntity<InOutConfiguration> response = template
				.postForEntity(ADMIN_URL, request, InOutConfiguration.class);

		Assert.assertEquals(CREATED, response.getStatusCode());
	}

	private InOutConfiguration config() {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "Kim Tae-yeon");
		headers.put("Reject", "Angelina Joulie");
		HTTPRequest request = new HTTPRequest("GET", "/korea/seoul", headers, "body");
		HTTPResponse response = new HTTPResponse(200, headers, "body");
		InOutConfiguration config = new InOutConfiguration("sample", request, response);
		return config;
	}

}
