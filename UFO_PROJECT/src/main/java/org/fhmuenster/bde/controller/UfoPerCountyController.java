package org.fhmuenster.bde.controller;

import java.util.ArrayList;
import java.util.List;

import org.fhmuenster.bde.entity.UfoPerCounty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UfoPerCountyController {

	@RequestMapping(value = "/ufopercounty", method = RequestMethod.GET)
	public String getPrimaryRessources(Model model) {
		List<UfoPerCounty> ufoPerCountyList = new ArrayList<UfoPerCounty>();
		UfoPerCounty county1 = new UfoPerCounty("Alameda County", 10);
		UfoPerCounty county2 = new UfoPerCounty("San Bernardino County", 10);
		UfoPerCounty county3 = new UfoPerCounty("Yolo County", 20);
		UfoPerCounty county4 = new UfoPerCounty("Lassen County", 5);
		UfoPerCounty county5 = new UfoPerCounty("Lake County", 30);
		ufoPerCountyList.add(county1);
		ufoPerCountyList.add(county2);
		ufoPerCountyList.add(county3);
		ufoPerCountyList.add(county4);
		ufoPerCountyList.add(county5);
		model.addAttribute("ufoPerCountyList", ufoPerCountyList);
		return "ufopercounty";
	}
}