package org.fhmuenster.bde.controller;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.fhmuenster.bde.entity.ShapeType;
import org.fhmuenster.bde.entity.UfoPerCounty;
import org.fhmuenster.bde.service.UfoPerShapeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Visualisierung der Ufos pro County und Art.
 */
@Controller
public class UfoPerShapeController {

	@Inject
	UfoPerShapeService ufoPerShapeService;

	@RequestMapping(value = "/ufopershape", method = RequestMethod.GET)
	public String getPrimaryRessources(Model model,
			@RequestParam(value = "shape") String shape) {
		List<UfoPerCounty> ufoPerShapeList = ufoPerShapeService
				.findFilterByShape(shape);
		model.addAttribute("ufoPerShapeList", ufoPerShapeList);
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