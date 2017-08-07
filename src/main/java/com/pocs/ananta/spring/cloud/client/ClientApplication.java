package com.pocs.ananta.spring.cloud.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class ClientApplication {

	@Autowired
	private EurekaClient eurekaClient;

	@Autowired
	private RestTemplateBuilder restTemplateBuilder;

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@RequestMapping("/")
	public String callService() {

		RestTemplate restTemplate = restTemplateBuilder.build();

		/*
		 * Alternatively, we can inject or Autowire DiscoveryClient client
		 * 
		 * List<ServiceInstance> instances = client.getInstances("service-id");
		 */

		// First arg is the service name and the second arg is to indicate
		// whether it is secured or not
		InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("spring-cloud-service", false);

		String baseUrl = instanceInfo.getHomePageUrl();

		ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.GET, null, String.class);

		return response.getBody();
	}
}
