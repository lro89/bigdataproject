package org.fhmuenster.bde.controller;

import java.util.List;

import javax.inject.Inject;

import org.fhmuenster.bde.entity.UfoAndTemperatureCorrelation;
import org.fhmuenster.bde.service.UfoAndTemperaturCorrelationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Visualisierung der Ufos korreliert mit der Temperatur in gesamt Kalifornien.
 */
@Controller
public class UfoAndTemperatureCorrelationController {

	@Inject
	UfoAndTemperaturCorrelationService ufoAndTemperatureCorrelationService;

	@RequestMapping(value = "/ufotempcorrelation", method = RequestMethod.GET)
	public String getPrimaryRessources(Model model) {
		List<UfoAndTemperatureCorrelation> ufoTempCorrList = ufoAndTemperatureCorrelationService
				.findAll();
		model.addAttribute("ufoTempCorrList", ufoTempCorrList);

		// Daten für das Chart als String zusammenbauen
		StringBuilder ufoTempCorrDataString = new StringBuilder("");
		for (UfoAndTemperatureCorrelation ufoTempCorr : ufoTempCorrList) {
			ufoTempCorrDataString.append("[\"");
			ufoTempCorrDataString.append(ufoTempCorr.getYearMonth());
			ufoTempCorrDataString.append("\",");
			ufoTempCorrDataString.append(ufoTempCorr.getValue());
			ufoTempCorrDataString.append(",");
			ufoTempCorrDataString.append(ufoTempCorr.getTemperature());
			ufoTempCorrDataString.append("],");
		}
		ufoTempCorrDataString.deleteCharAt(ufoTempCorrDataString.length() - 1);
		model.addAttribute("ufoTempCorrData", ufoTempCorrDataString.toString());
		return "ufoandtemperaturecorrelation";
	}
}