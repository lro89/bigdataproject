package org.fhmuenster.bde.service;

import java.util.List;

import org.fhmuenster.bde.entity.UfoAndTemperatureCorrelation;

public interface UfoAndTemperaturCorrelationService {

	/**
	 * Lädt alle UFO und Temeraturkorrelationen aus HBase.
	 */
	List<UfoAndTemperatureCorrelation> findAll();

}
