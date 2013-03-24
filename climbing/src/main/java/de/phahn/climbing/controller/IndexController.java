package de.phahn.climbing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.phahn.climbing.controller.command.RegistrationCommand;

@Controller
@RequestMapping("/")
public class IndexController {

	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model) {	
		model.addAttribute("command", new RegistrationCommand());		
		return "index";
	}
}
