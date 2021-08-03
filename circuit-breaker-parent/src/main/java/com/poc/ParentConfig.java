package com.poc;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ParentConfig {

	@Bean
	public RestTemplate restConventional(RestTemplateBuilder builder) {

		// Possible timeouts, no they can also support in circuit breaking in
		// traditional terms by virtue
		return builder.setConnectTimeout(Duration.ofMillis(3000))
				.setReadTimeout(Duration.ofMillis(3000)).build();
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {

		// Possible timeouts, no they can also support in circuit breaking in
		// traditional terms by virtue
		return builder.build();
	}
}
