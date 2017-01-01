package com.weijuly.develop.ras.config;

import com.weijuly.develop.ras.data.InOutConfiguration;
import com.weijuly.develop.ras.impl.Admin;
import com.weijuly.develop.ras.impl.Simulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@EnableAspectJAutoProxy
public class SimulatorController {

	private final AtomicLong counter = new AtomicLong();

	@Autowired
	Admin admin;

	@Autowired
	Simulator simulator;

	@RequestMapping(path = "/simulator", method = GET)
	public ResponseEntity<String> simulate(HttpServletRequest request) {
		return simulator.simulate(request);
	}

	@RequestMapping(path = "/admin/{id}", method = GET)
	public ResponseEntity<String> admin(@PathVariable Long id) {
		return admin.get(id);
	}

	@RequestMapping(path = "/admin", method = GET)
	public ResponseEntity<String> list() {
		return admin.list();
	}

	@RequestMapping(path = "/admin", method = POST)
	@ResponseStatus(CREATED)
	public ResponseEntity<String> create(@RequestBody InOutConfiguration config) {
		return admin.create(config);
	}

	@RequestMapping(path = "/admin/{id}", method = DELETE)
	public ResponseEntity<String> delete(@PathVariable Long id) {
		return admin.delete(id);
	}

}
