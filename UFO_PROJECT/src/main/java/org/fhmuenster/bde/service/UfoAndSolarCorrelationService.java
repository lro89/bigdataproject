package org.fhmuenster.bde.service;

import java.util.List;

import org.fhmuenster.bde.entity.UfoAndSolarCorrelation;

public interface UfoAndSolarCorrelationService {

	/**
	 * Lädt alle UFO und Sonnenkraftkorrelationen aus HBase.
	 */
	List<UfoAndSolarCorrelation> findAll();

}
