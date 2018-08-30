package com.mss.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {
	
	
	@Autowired
	private CurrencyExchangeServiceProxy currencyExchangeServiceProxy;
	
   	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConvesionBean convertCurrency(@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {
		Map<String,String> uriVariables = new HashMap<String,String>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		ResponseEntity<CurrencyConvesionBean> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConvesionBean.class,uriVariables);
		CurrencyConvesionBean response = responseEntity.getBody();
		CurrencyConvesionBean currencyConvesionBean = new CurrencyConvesionBean(response.getId(),from,to,quantity,
				response.getConversionMultiple(),
				quantity.multiply(response.getConversionMultiple()),response.getPort());
		return currencyConvesionBean;
	}
   	
   	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConvesionBean convertCurrencyFeign(@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {
   		CurrencyConvesionBean response = currencyExchangeServiceProxy.retrieveExchangeValue(from, to);
   		
		CurrencyConvesionBean currencyConvesionBean = new CurrencyConvesionBean(response.getId(),from,to,quantity,
				response.getConversionMultiple(),
				quantity.multiply(response.getConversionMultiple()),response.getPort());
		return currencyConvesionBean;
	}
	
	
}
