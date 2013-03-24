package de.phahn.climbing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.phahn.climbing.controller.command.RegistrationCommand;
import de.phahn.climbing.model.User;
import de.phahn.climbing.service.RegistrationService;
import de.phahn.climbing.service.UserDetailsServiceImpl;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
	
	@Autowired
	private RegistrationService service;
	
	@Autowired
	private UserDetailsServiceImpl manager;
	
	@RequestMapping(method=RequestMethod.POST)
	public String register(RegistrationCommand command) {
		User user = service.register(command);
		
		// log in user		
		UserDetails userDetails = manager.loadUserByUsername (user.getUsername ());
		Authentication auth = new UsernamePasswordAuthenticationToken (userDetails.getUsername (),userDetails.getPassword (),userDetails.getAuthorities ());
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		return "redirect:/userdetails" ;
	}
}
