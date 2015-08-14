package org.fhmuenster.bde.controller;

import javax.inject.Inject;

import org.fhmuenster.bde.service.CitiesCountiesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	// test hbase
	@Inject
	CitiesCountiesService citiesCountiesService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getPrimaryRessources(Model model) {
		// Test hbase
		citiesCountiesService.findAll();
		return "main";
	}
}