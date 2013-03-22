package de.phahn.climbing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.phahn.climbing.model.Requirement;
import de.phahn.climbing.service.RequirementService;

@Controller
@RequestMapping("/requirements")
public class RequirementController {
	
	@Autowired
	private RequirementService requirementService;
	
	@RequestMapping(value="create", method=RequestMethod.GET)
	public String show(Model model) {
		Requirement req = new Requirement();
		model.addAttribute("command", req);
		return "requirements/create";
	}
	
	@RequestMapping(value="create", method=RequestMethod.POST)
	public String create(Requirement req) {	
		requirementService.save(req);
		return "redirect:/requirements";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String list(Model model) {	
		List<Requirement> requirements = requirementService.list();
		model.addAttribute("requirements", requirements);
		return "requirements/list";
	}

}
