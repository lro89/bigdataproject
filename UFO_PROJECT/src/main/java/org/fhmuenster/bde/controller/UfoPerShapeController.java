package org.fhmuenster.bde.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.fhmuenster.bde.entity.ShapeType;
import org.fhmuenster.bde.entity.UfoPerCounty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Visualisierung der Ufos pro County und Art.
 *
 */
@Controller
public class UfoPerShapeController {

	@RequestMapping(value = "/ufopershape", method = RequestMethod.GET)
	public String getPrimaryRessources(Model model,
			@RequestParam(value = "shape") String shape) {
		// Testdate
		// FIXME aus HBase holen by shape
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
		model.addAttribute("shape", shape);

		return "ufopershape";
	}

	/**
	 * Alle Shape-Arten zur Verfügung stellen.
	 */
	@ModelAttribute("allShapes")
	public Set<ShapeType> populateShapes() {
		return ShapeType.ALL;
	}

}