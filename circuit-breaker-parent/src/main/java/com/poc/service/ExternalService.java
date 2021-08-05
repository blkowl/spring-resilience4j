package com.poc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@Service
public class ExternalService {

	@Autowired
	private RestTemplate restTemplate;
	
    @CircuitBreaker(name = "mainService")
	public String getChildService() {
		
		String response = restTemplate.getForObject("http://localhost:8090", String.class);
		
		 return response;
	}
    
	// The fallback method should have the same signature of 
	// that of the main method for which the fallback is written.
    private String testFallBack(Exception e){
        return "In fallback method";
    }
	
}
