package com.spring.springbootconfig.resource;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.springbootconfig.bean.DBConfig;

@RestController
public class GreetingController {
	
	@Value("${my.greeting}")
	private String greetingMessage;
	
	@Value("${my.list.value}")
	private List<String> listValue;
	
	/*
	 * @Value("#{${dbvalue}}") private Map<String, String> dbValue;
	 */
	
	@Autowired
	private DBConfig dbConfig;
	
	
	@GetMapping("/greet")
	public String getGreeting() {
		//return greetingMessage+" "+listValue+" "+dbValue;
		
		return dbConfig.getConnection()+"  "+dbConfig.getHost()+"  "+dbConfig.getPort();
	}

}
