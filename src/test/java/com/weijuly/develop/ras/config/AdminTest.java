package com.weijuly.develop.ras.config;

import static org.junit.Assert.assertNotNull;
import static org.springframework.http.HttpStatus.CREATED;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weijuly.develop.ras.data.InOutConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@TestExecutionListeners(listeners = {
		DependencyInjectionTestExecutionListener.class })
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

	@Test
	public void adminShouldRespond() {
		ResponseEntity<String> response = template.getForEntity(ADMIN_URL,
				String.class);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		String string = response.getBody();
		assertNotNull(string);
	}

	@Test
	public void shouldAcceptAndReturnConfig() throws JsonProcessingException {
		InOutConfiguration config = createSampleConfig();

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(
				mapper.writeValueAsString(config), requestHeaders);
		ResponseEntity<InOutConfiguration> response = template
				.postForEntity(ADMIN_URL, request, InOutConfiguration.class);

		Assert.assertEquals(CREATED, response.getStatusCode());
		Assert.assertEquals(config, response.getBody());
	}

	@Test
	public void shouldPersistConfig() throws JsonProcessingException {
		InOutConfiguration config = createSampleConfig();

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(
				mapper.writeValueAsString(config), requestHeaders);
		ResponseEntity<InOutConfiguration> response = template
				.postForEntity(ADMIN_URL, request, InOutConfiguration.class);

		Assert.assertEquals(CREATED, response.getStatusCode());
		Assert.assertEquals(config, response.getBody());
	}

	private InOutConfiguration createSampleConfig() {
		InOutConfiguration config = new InOutConfiguration("sample", "request",
				"response");
		return config;
	}

}
