package org.fhmuenster.bde.controller;

import java.util.ArrayList;
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
		// List<UfoPerCounty> ufoPerCountyList =ufoPerCountyService.findAll();
		// FIXME aus HBase holen
		// Testdate
		List<UfoPerCounty> ufoPerCountyList = new ArrayList<UfoPerCounty>();
		UfoPerCounty county1 = new UfoPerCounty("us-ca-051", 50, "Mono County");
		UfoPerCounty county2 = new UfoPerCounty("us-ca-071", 20,
				"San Bernardino County");
		UfoPerCounty county3 = new UfoPerCounty("us-ca-113", 90, "Yolo County");
		UfoPerCounty county4 = new UfoPerCounty("us-ca-033", 30, "Lake County");
		ufoPerCountyList.add(county1);
		ufoPerCountyList.add(county2);
		ufoPerCountyList.add(county3);
		ufoPerCountyList.add(county4);
		model.addAttribute("ufoPerCountyList", ufoPerCountyList);

		return "ufopercounty";
	}
}