package org.fhmuenster.bde.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.fhmuenster.bde.entity.UfoPerCounty;
import org.fhmuenster.bde.repository.UfoPerShapeRepository;
import org.fhmuenster.bde.service.UfoPerShapeService;
import org.springframework.stereotype.Service;

@Service
public class UfoPerShapeServiceImpl implements UfoPerShapeService {

	@Inject
	UfoPerShapeRepository ufoPerShapeRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fhmuenster.bde.service.UfoPerShapeService#findFilterByShape(java.
	 * lang.String)
	 */
	@Override
	public List<UfoPerCounty> findFilterByShape(String shape) {
		List<UfoPerCounty> ufoPerShapeList = ufoPerShapeRepository
				.findFilterByShape(shape);
		return ufoPerShapeList;
	}
}
