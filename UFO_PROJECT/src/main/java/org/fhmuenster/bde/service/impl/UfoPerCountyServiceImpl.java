package org.fhmuenster.bde.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.fhmuenster.bde.entity.UfoPerCounty;
import org.fhmuenster.bde.repository.UfoPerCountyRepository;
import org.fhmuenster.bde.service.UfoPerCountyService;
import org.springframework.stereotype.Service;

@Service
public class UfoPerCountyServiceImpl implements UfoPerCountyService {

	@Inject
	UfoPerCountyRepository ufoPerCountyRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fhmuenster.bde.service.UfoPerCountyService#findAll()
	 */
	@Override
	public List<UfoPerCounty> findAll() {
		List<UfoPerCounty> ufoPerCountyList = ufoPerCountyRepository.findAll();
		// FIXME Ausgabe rausnehmen
		System.out.println("Number of ufosPerCounty = "
				+ ufoPerCountyList.size());
		System.out.println(ufoPerCountyList);
		return ufoPerCountyList;
	}
}
