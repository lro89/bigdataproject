package org.fhmuenster.bde.service;

import java.util.List;

import org.fhmuenster.bde.entity.UfoPerCounty;

public interface UfoPerShapeService {

	/**
	 * Lädt alle UFOs pro County gefiltert auf eine bestimmte Shape-Art aus
	 * HBase.
	 */
	List<UfoPerCounty> findFilterByShape(String shape);

}
