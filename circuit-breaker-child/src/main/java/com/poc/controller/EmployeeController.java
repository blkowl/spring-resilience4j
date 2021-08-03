package com.poc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	@GetMapping
	public String getBanner() throws InterruptedException {
	
		
		Thread.sleep(15000);
		
		return "Child Controller is up !!!!";
	}
	
}

