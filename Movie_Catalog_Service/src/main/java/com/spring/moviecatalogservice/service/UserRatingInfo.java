package com.spring.moviecatalogservice.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spring.moviecatalogservice.model.Rating;
import com.spring.moviecatalogservice.model.UserRating;

@Service
public class UserRatingInfo {
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@HystrixCommand(fallbackMethod = "getFallbackUserRating")
	public UserRating getUserRating(String userId) {
		return restTemplate.getForObject("http://rating-data-service/rating/user/"+userId, UserRating.class);
	}
	
	public UserRating getFallbackUserRating(String userId) {
		
		UserRating userRating = new UserRating();
		userRating.setUserId(userId);
		userRating.setUserRating(Arrays.asList(new Rating("0", 0) ));
		return userRating;
		
	}

}
