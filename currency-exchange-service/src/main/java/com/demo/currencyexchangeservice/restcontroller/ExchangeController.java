package com.demo.currencyexchangeservice.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.demo.currencyexchangeservice.bean.ExchangeValue;
import com.demo.currencyexchangeservice.repository.ExchangeValueRepository;

@RestController
public class ExchangeController {
	
	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Environment environment;

	@Autowired
	private ExchangeValueRepository exchangerepo;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to){

		ExchangeValue exchangeValue = exchangerepo.findByFromAndTo(from, to);
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		LOGGER.info("currency-exchange log - response: {}", exchangeValue);
		return exchangeValue;
	}

}
