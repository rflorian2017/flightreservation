package com.rosu.flights.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.rosu.flights.model.Passenger;
import com.rosu.flights.model.PassengerModel;
import com.rosu.flights.services.PassengerService;

@Controller
public class PassengerViewController {

	private PassengerService passengerService;

	@Autowired
	protected PassengerViewController(PassengerService passengerService) {
		this.passengerService = passengerService;
	}
	
	@GetMapping("/passengers")
	public String getPassengers(Model model) {
		List<Passenger> passengers = this.passengerService.getAllPassengers();
		model.addAttribute("passengers", passengers);
		return "passengers-view";
	}
	
	@GetMapping("/passengers/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN'")
	public String getPassenger(Model model, @PathVariable Long id) {
		Passenger passenger = this.passengerService.getPassenger(id);
		
		model.addAttribute("passenger", passenger);
		return "passenger-view";
	}
	
	@PostMapping("/passengers/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN'")
	public String updatePassenger(Model model, @PathVariable Long id, @ModelAttribute PassengerModel passengerModel) {
		Passenger passenger = this.passengerService.updatePassenger(passengerModel, id);
		
		model.addAttribute("passenger", passenger);
		model.addAttribute("passengerModel", new PassengerModel());
		return "redirect:/passengers";
	}
	
}
