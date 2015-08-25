package org.fhmuenster.bde.controller;

import java.util.List;

import javax.inject.Inject;

import org.fhmuenster.bde.entity.UfoPerCounty;
import org.fhmuenster.bde.service.UfoPerCountyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Visualisierung aller Ufos pro County.
 */
@Controller
public class UfoPerCountyController {

	@Inject
	UfoPerCountyService ufoPerCountyService;

	@RequestMapping(value = "/ufopercounty", method = RequestMethod.GET)
	public String getPrimaryRessources(Model model) {
		List<UfoPerCounty> ufoPerCountyList = ufoPerCountyService.findAll();
		model.addAttribute("ufoPerCountyList", ufoPerCountyList);
		return "ufopercounty";
	}
}