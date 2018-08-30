package com.mss.microservices.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfiguratonController {
	
	@Autowired
	private Configuration configuration;
	
	@GetMapping("/limits")
	public LimitsConfiguration retrieveLimtsConfigurations() {
		LimitsConfiguration limitsConfiguration = new LimitsConfiguration(configuration.getMinimum(),configuration.getMaximum());
		return limitsConfiguration;
	}

}
