package com.spring.moviecatalogservice.resource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spring.moviecatalogservice.model.CatalogItem;
import com.spring.moviecatalogservice.model.Movie;
import com.spring.moviecatalogservice.model.Rating;
import com.spring.moviecatalogservice.model.UserRating;
import com.spring.moviecatalogservice.service.MovieInfo;
import com.spring.moviecatalogservice.service.UserRatingInfo;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Autowired
	private MovieInfo movieInfo;
	
	@Autowired
	private UserRatingInfo userRatingInfo;
	
	@RequestMapping("/{userId}")
	//@HystrixCommand(fallbackMethod = "getFallbackCatalog")
	public List<CatalogItem> getCatalog(@PathVariable String userId){
		
		
		
		UserRating ratings = userRatingInfo.getUserRating(userId);
		
		return ratings.getUserRating().stream().map(rating ->  movieInfo.getCatalogItem(rating))
			.collect(Collectors.toList());
		
	}
	
	
	
	/*
	 * public List<CatalogItem> getFallbackCatalog(@PathVariable String userId){
	 * return Arrays.asList(new CatalogItem("No movie", "", 0)); }
	 */

}

/*
 * webClientBuilder.build() .get()
 * .uri("http://localhost:8082/movie/"+rating.getMovieId())
 * .bodyToMono(Movie.class) .build();
 */

