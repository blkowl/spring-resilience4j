package com.poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class ParentController {

	private static final String MAIN_SERVICE = "mainService";
	
	
	@Autowired
	private RestTemplate restConventional;
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@GetMapping("timeout")
	public String getConventionalWithTimeout() {
		
		
		ResponseEntity<String> response = restConventional.getForEntity("http://localhost:8090", String.class);
		
		String data = response.getBody();
		
		System.out.println(data);
		
		return "Parent controller Conventional with Timeout" + data;
	}
	
	@GetMapping("infinite")
	public String getConventionalWithoutTimeout() {
		
		
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8090", String.class);
		
		String data = response.getBody();
		
		System.out.println(data);
		
		return "Parent controller Conventional " + data;
	}
	
	
	@GetMapping("basic-resilience")
    @CircuitBreaker(name = "mainService", fallbackMethod="testFallBack")
	public ResponseEntity<String> getBasicReslianceSupport() {
		
		String response = restTemplate.getForObject("http://localhost:8081/serviceOne", String.class);
        return new ResponseEntity<String>(response, HttpStatus.OK);
		
		//return "Parent controller Conventional " + data;
	}
	
    private  ResponseEntity<String> testFallBack(Exception e){
        return new ResponseEntity<String>("In fallback method", HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	
}
