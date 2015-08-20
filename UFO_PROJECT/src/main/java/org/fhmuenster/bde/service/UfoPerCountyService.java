package org.fhmuenster.bde.service;

import java.util.List;

import org.fhmuenster.bde.entity.UfoPerCounty;

public interface UfoPerCountyService {

	/**
	 * Lädt alle UFOs pro County aus HBase.
	 */
	List<UfoPerCounty> findAll();

}
