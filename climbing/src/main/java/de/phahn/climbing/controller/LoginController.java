package de.phahn.climbing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.phahn.climbing.service.RegistrationService;
import de.phahn.climbing.service.UserDetailsServiceImpl;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private RegistrationService service;
	
	@Autowired
	private UserDetailsServiceImpl manager;
	
	@RequestMapping(method=RequestMethod.GET)
	public String register() {		
		return "login" ;
	}
}
