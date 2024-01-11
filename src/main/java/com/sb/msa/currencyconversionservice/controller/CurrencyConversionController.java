package com.sb.msa.currencyconversionservice.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sb.msa.currencyconversionservice.CurrencyExchangeProxy;
import com.sb.msa.currencyconversionservice.currencyconversion.CurrencyConversion;

@RestController
public class CurrencyConversionController {
	@Autowired
	private Environment env;
	
	@Autowired
	private CurrencyExchangeProxy currencyExchangeProxy;
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{qty}")
	public ResponseEntity<CurrencyConversion> getCurrencyConversion(
			@PathVariable("from") String currencyFrom, @PathVariable("to") String currencyTo, @PathVariable("qty") int qty) {
		HashMap<String, String> ceUriVariables = new HashMap<>();
		ceUriVariables.put("from", currencyFrom);
		ceUriVariables.put("to", currencyTo);
		ResponseEntity<CurrencyConversion> cere = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
				CurrencyConversion.class, ceUriVariables);
		CurrencyConversion cc = cere.getBody();
		int totalAmt = qty * cc.getConversionMultiple();
		return new ResponseEntity<>(new CurrencyConversion(cc.getId(), cc.getCurrencyfrom(), cc.getCurrencyto(), cc.getConversionMultiple(), qty, totalAmt, 
				cc.getEnv()), HttpStatus.OK);
	}
	
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{qty}")
	public ResponseEntity<CurrencyConversion> getCurrencyConversionFeign(
			@PathVariable("from") String currencyFrom, @PathVariable("to") String currencyTo, @PathVariable("qty") int qty) {
		ResponseEntity<CurrencyConversion> ccre = currencyExchangeProxy.getExchange(currencyFrom, currencyTo);
		CurrencyConversion cc = ccre.getBody();
		int totalAmt = qty * cc.getConversionMultiple();
		return new ResponseEntity<>(new CurrencyConversion(cc.getId(), cc.getCurrencyfrom(), cc.getCurrencyto(), cc.getConversionMultiple(), qty, totalAmt, 
				cc.getEnv()), HttpStatus.OK);
	}
}