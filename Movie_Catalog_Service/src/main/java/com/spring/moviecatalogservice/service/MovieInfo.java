package com.spring.moviecatalogservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spring.moviecatalogservice.model.CatalogItem;
import com.spring.moviecatalogservice.model.Movie;
import com.spring.moviecatalogservice.model.Rating;

@Service
public class MovieInfo {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
	public CatalogItem getCatalogItem(Rating rating) {
		Movie movie = restTemplate.getForObject("http://movie-info-service/movie/"+rating.getMovieId(), Movie.class);
		
		
		return new CatalogItem(movie.getName(), "Test", rating.getRating());
	}
	
	public CatalogItem getFallbackCatalogItem(Rating rating) {
		
		return new CatalogItem("Movie not found", "", rating.getRating());
		
	}

}
