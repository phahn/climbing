package de.phahn.climbing.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.phahn.climbing.model.User;
import de.phahn.climbing.service.RegistrationService;
import de.phahn.climbing.service.UserDetailsServiceImpl;

@Controller
@RequestMapping("/userdetails")
public class DetailsController {
	
	@Autowired
	private RegistrationService service;
	
	@Autowired
	private UserDetailsServiceImpl manager;
	
	@RequestMapping(method=RequestMethod.GET)
	public String register(Principal principal, Model model) {
		
		User user = service.findUserByUsername(principal.getName());
		model.addAttribute("user", user);
		return "details" ;
	}
}
