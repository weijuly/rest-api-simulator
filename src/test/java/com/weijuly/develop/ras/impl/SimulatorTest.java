package com.weijuly.develop.ras.impl;

import com.weijuly.develop.ras.config.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
@WebIntegrationTest
public class SimulatorTest {

	RestTemplate template = null;

	private final String ADMIN_URL = "http://localhost:8080/admin";
	private final String SIMUL_URL = "http://localhost:8080/simulator";

	@Before
	public void prepare(){
		template = new TestRestTemplate();
	}

	@Test
	public void test(){
		template.getForEntity(SIMUL_URL, String.class);
	}
}
