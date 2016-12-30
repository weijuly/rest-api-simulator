package com.weijuly.develop.ras.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weijuly.develop.ras.data.HTTPRequest;
import com.weijuly.develop.ras.data.HTTPResponse;
import com.weijuly.develop.ras.data.InOutConfiguration;
import com.weijuly.develop.ras.persist.InOutConfigurationDAO;
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
import static org.springframework.http.HttpStatus.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
@WebIntegrationTest
public class AdminTest {

	RestTemplate template = new TestRestTemplate();
	HttpHeaders requestHeaders = null;
	String name = "sample";

	@Autowired
	ObjectMapper mapper;

	@Autowired
	InOutConfigurationDAO dao;

	private final String ADMIN_URL = "http://localhost:8080/admin";

	@Before
	public void setup() {
		requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		dao.deleteAll();
	}

	@Test
	public void adminShouldRespond() {
		ResponseEntity<String> response = template.getForEntity(ADMIN_URL,
				String.class);
		assertEquals(METHOD_NOT_ALLOWED, response.getStatusCode());
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

		assertEquals(BAD_REQUEST, response.getStatusCode());
	}

	@Test
	public void shouldPersistConfig() throws JsonProcessingException {

		InOutConfiguration config = config();
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(
				mapper.writeValueAsString(config), requestHeaders);
		ResponseEntity<InOutConfiguration> response = template
				.postForEntity(ADMIN_URL, request, InOutConfiguration.class);

		assertEquals(CREATED, response.getStatusCode());
		assertNotNull(response.getBody());
		assertNotNull(response.getBody().getId());
		assertEquals(name, response.getBody().getName());

		response = template.getForEntity(String.format("%s/%d", ADMIN_URL, response.getBody().getId()),
				InOutConfiguration.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(name, response.getBody().getName());

	}

	@Test
	public void shouldReturnNotFoundWhenPassedNonExistentId() {
		ResponseEntity<String> response = template.getForEntity(ADMIN_URL + "/43046", String.class);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	private InOutConfiguration config() {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "Kim Tae-yeon");
		headers.put("Reject", "Angelina Joulie");
		HTTPRequest request = new HTTPRequest("GET", "/korea/seoul", headers, "body");
		HTTPResponse response = new HTTPResponse(200, headers, "body");
		InOutConfiguration config = new InOutConfiguration(null, name, request, response);
		return config;
	}

}
