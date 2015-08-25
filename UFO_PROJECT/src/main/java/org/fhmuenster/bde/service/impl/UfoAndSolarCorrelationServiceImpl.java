package org.fhmuenster.bde.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.fhmuenster.bde.entity.UfoAndSolarCorrelation;
import org.fhmuenster.bde.repository.UfoAndSolarCorrelationRepository;
import org.fhmuenster.bde.service.UfoAndSolarCorrelationService;
import org.springframework.stereotype.Service;

@Service
public class UfoAndSolarCorrelationServiceImpl implements
		UfoAndSolarCorrelationService {

	@Inject
	UfoAndSolarCorrelationRepository ufoAndSolarCorrelationRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fhmuenster.bde.service.UfoAndSolarCorrelationService#findAll()
	 */
	@Override
	public List<UfoAndSolarCorrelation> findAll() {
		List<UfoAndSolarCorrelation> ufoAndTempList = ufoAndSolarCorrelationRepository
				.findAll();
		return ufoAndTempList;
	}
}
