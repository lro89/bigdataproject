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
 * Visualisierung der Ufos pro County und Art.
 */
@Controller
public class UfoAndTemperatureCorrelationController {

	@Inject
	UfoAndTemperaturCorrelationService ufoAndTemperatureCorrelationService;

	@RequestMapping(value = "/ufotempcorrelation", method = RequestMethod.GET)
	public String getPrimaryRessources(Model model) {
		List<UfoAndTemperatureCorrelation> ufoTempCorrList = ufoAndTemperatureCorrelationService
				.findAll();

		// Testdaten
		// List<UfoAndTemperatureCorrelation> ufoTempCorrList = new
		// ArrayList<UfoAndTemperatureCorrelation>();
		// UfoAndTemperatureCorrelation uatc1 = new
		// UfoAndTemperatureCorrelation(
		// "2014", "01", 10, 5);
		// UfoAndTemperatureCorrelation uatc2 = new
		// UfoAndTemperatureCorrelation(
		// "2014", "02", 20, 10);
		// UfoAndTemperatureCorrelation uatc3 = new
		// UfoAndTemperatureCorrelation(
		// "2014", "05", 30, 23);
		// UfoAndTemperatureCorrelation uatc4 = new
		// UfoAndTemperatureCorrelation(
		// "2015", "01", 20, 3);
		// ufoTempCorrList.add(uatc1);
		// ufoTempCorrList.add(uatc2);
		// ufoTempCorrList.add(uatc3);
		// ufoTempCorrList.add(uatc4);
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