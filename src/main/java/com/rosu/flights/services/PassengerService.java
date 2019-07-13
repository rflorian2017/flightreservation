package com.rosu.flights.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;

import com.rosu.flights.model.Passenger;
import com.rosu.flights.model.PassengerModel;

@Service
public class PassengerService {
	
	@Value("${reservation.service.url}")
	private String passengerServiceUrl;
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	public List<Passenger> getAllPassengers() {
		System.out.println(passengerServiceUrl);
		String url = passengerServiceUrl + "/passengers";
		HttpEntity<String> request = new HttpEntity<String>(null, null);
		return this.restTemplate.
				exchange(url, HttpMethod.GET,  request, new ParameterizedTypeReference<List<Passenger>>() {})
				.getBody();
		
	}
	
	public Passenger getPassenger(Long id) {
		System.out.println(passengerServiceUrl);
		String url = passengerServiceUrl + "/passengers/" + id;
		HttpEntity<String> request = new HttpEntity<String>(null, null);
		return this.restTemplate.
				exchange(url, HttpMethod.GET,  request, new ParameterizedTypeReference<Passenger>() {})
				.getBody();
		
	}
	
	public Passenger updatePassenger(PassengerModel passengerModel, Long id) {
	
		String url = passengerServiceUrl + "/passengers/" + id;
		HttpEntity<PassengerModel> request = new HttpEntity<PassengerModel>(passengerModel, null);
		return this.restTemplate.
				exchange(url, HttpMethod.PUT, request, Passenger.class)
				.getBody();
		
	}

}
