package com.weijuly.develop.ras.config;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weijuly.develop.ras.data.Item;

@Controller
@RequestMapping("/simulator")
public class SimulatorController {

	private final AtomicLong counter = new AtomicLong();

	@RequestMapping(method = GET)
	public @ResponseBody Item item() {
		return new Item(counter.incrementAndGet(), "foosa");
	}

}
