package com.weijuly.develop.ras.config;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.weijuly.develop.ras.data.InOutConfiguration;
import com.weijuly.develop.ras.data.Item;
import com.weijuly.develop.ras.impl.Admin;

@RestController
@EnableAspectJAutoProxy
public class SimulatorController {

	private final AtomicLong counter = new AtomicLong();
	
	@Autowired
	Admin admin;

	@RequestMapping(path = "/simulator", method = GET)
	public Item item() {
		return new Item(counter.incrementAndGet(), "foosa");
	}

	@RequestMapping(path = "/admin", method = GET)
	public String admin() {
		return "admin console";
	}

	@RequestMapping(path = "/admin", method = POST)
	@ResponseStatus(CREATED)
	public InOutConfiguration create(@RequestBody InOutConfiguration config) {
		return admin.create(config);
	}

}
