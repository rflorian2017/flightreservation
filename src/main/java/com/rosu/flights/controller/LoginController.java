package com.rosu.flights.controller;

import java.security.Principal;

import org.springframework.boot.actuate.trace.http.HttpTrace.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping(value = {"/", "/welcome"})
	public String welcomePage(Model model) {
		model.addAttribute("title", "Welcome!");
		model.addAttribute("message", "This is a login test !");
		return "welcomePage";
		
	}
	//@RequestMapping(value = "/login", method = RequestMethod.GET)
	@GetMapping(value = "/login")
	public String loginPage(Model model) {
		return "loginPage";
	}
	
	@GetMapping(value = "/logoutDone")
	public String logoutPage(Model model) {
		return "logoutPage";
	}
	
	@GetMapping(value = "/userinfo")
	public String userInfo(Model model, Principal principal) {
		// after login successful
		String userName = principal.getName();
		
		System.out.println(userName);
		
		return "userInfoPage";
	}
	
	@GetMapping(value = "/admin")
	public String adminInfo(Model model, Principal principal) {
		// after login successful
		String userName = principal.getName();
		
		System.out.println(userName);
		
		return "adminInfoPage";
	}

}
