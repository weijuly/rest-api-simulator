package com.weijuly.develop.ras.config;

import static org.junit.Assert.assertEquals;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weijuly.develop.ras.data.Item;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@TestExecutionListeners(listeners = {
		DependencyInjectionTestExecutionListener.class })
@WebIntegrationTest
public class SimulatorTest {

	RestTemplate template = new TestRestTemplate();

	private final String SIMULATOR_URL = "http://localhost:8080/simulator";

	@Test
	public void simulatorShouldRespond() {

		ResponseEntity<Item> response = template.getForEntity(SIMULATOR_URL,
				Item.class);
		Item item = response.getBody();
		assertEquals(Long.valueOf(1L), item.getNumber());
		assertEquals("foosa", item.getMessage());

	}

}
