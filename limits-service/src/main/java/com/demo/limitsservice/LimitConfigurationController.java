package com.demo.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.limitsservice.bean.LimitConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class LimitConfigurationController {
	
	@Autowired
	private Configuration configuration;
	
	@GetMapping("/limits")
	@HystrixCommand(fallbackMethod="fallBackretriveLimitConfiguration")
	public LimitConfiguration retriveLimitConfiguration() {
		
		return new LimitConfiguration(configuration.getMaximum() , configuration.getMinimum());
		
	}
	
	@GetMapping("/fault-limits")
	@HystrixCommand(fallbackMethod="fallBackretriveLimitConfiguration")
	public LimitConfiguration retriveConfiguration() {
		
		throw new RuntimeException("There is an error!");
		
	}
	

	public LimitConfiguration fallBackretriveLimitConfiguration() {
		
		return new LimitConfiguration(1000,10);
		
	}

}
