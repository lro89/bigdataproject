package org.fhmuenster.bde.controller;

import java.util.List;

import javax.inject.Inject;

import org.fhmuenster.bde.entity.UfoAndSolarCorrelation;
import org.fhmuenster.bde.service.UfoAndSolarCorrelationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Visualisierung der Ufos korreliert mit der Sonnenkraft in gesamt Kalifornien.
 */
@Controller
public class UfoAndSolarCorrelationController {

	@Inject
	UfoAndSolarCorrelationService ufoAndSolarCorrelationService;

	@RequestMapping(value = "/ufosolarcorrelation", method = RequestMethod.GET)
	public String getPrimaryRessources(Model model) {
		List<UfoAndSolarCorrelation> ufoSolarCorrList = ufoAndSolarCorrelationService
				.findAll();
		model.addAttribute("ufoSolarCorrList", ufoSolarCorrList);

		// Daten für das Chart als String zusammenbauen
		StringBuilder ufoSolarCorrDataString = new StringBuilder("");
		for (UfoAndSolarCorrelation ufoSolarCorr : ufoSolarCorrList) {
			ufoSolarCorrDataString.append("[\"");
			ufoSolarCorrDataString.append(ufoSolarCorr.getYearMonth());
			ufoSolarCorrDataString.append("\",");
			ufoSolarCorrDataString.append(ufoSolarCorr.getValue());
			ufoSolarCorrDataString.append(",");
			ufoSolarCorrDataString.append(ufoSolarCorr.getSolar());
			ufoSolarCorrDataString.append("],");
		}
		ufoSolarCorrDataString
				.deleteCharAt(ufoSolarCorrDataString.length() - 1);
		model.addAttribute("ufoSolarCorrData",
				ufoSolarCorrDataString.toString());
		return "ufoandsolarcorrelation";
	}
}