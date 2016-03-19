package com.weijuly.develop.ras.config;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.weijuly.develop.ras.data.Item;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest
public class SimulatorTest {

	@Test
	public void simulatorShouldRespond() {
		RestTemplate template = new TestRestTemplate();
		
		ResponseEntity<Item> response = template.getForEntity("http://localhost:8080/simulator", Item.class);
		Item item = response.getBody();
		assertEquals(Long.valueOf(1L), item.getNumber());
		assertEquals("foosa", item.getMessage());

	}

}
