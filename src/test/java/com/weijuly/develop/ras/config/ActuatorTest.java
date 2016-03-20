package com.weijuly.develop.ras.config;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.util.Assert.notNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@TestExecutionListeners(listeners = {
		DependencyInjectionTestExecutionListener.class })
@WebIntegrationTest
public class ActuatorTest {
	
	RestTemplate template = new TestRestTemplate();
	
	private final String HEALTH_URL = "http://localhost:8080/health";
	
	@Test
	public void shouldReportHealth(){
		ResponseEntity<String> response = template.getForEntity(HEALTH_URL, String.class);
		notNull(response);
		assertEquals(OK, response.getStatusCode());
	}

}
