package org.fhmuenster.bde.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.fhmuenster.bde.entity.UfoAndTemperatureCorrelation;
import org.fhmuenster.bde.repository.UfoAndTemperatureCorrelationRepository;
import org.fhmuenster.bde.service.UfoAndTemperaturCorrelationService;
import org.springframework.stereotype.Service;

@Service
public class UfoAndTemperatureCorrelationServiceImpl implements
		UfoAndTemperaturCorrelationService {

	@Inject
	UfoAndTemperatureCorrelationRepository ufoAndTemperatureCorrelationRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fhmuenster.bde.service.UfoAndTemperaturCorrelationService#findAll()
	 */
	@Override
	public List<UfoAndTemperatureCorrelation> findAll() {
		List<UfoAndTemperatureCorrelation> ufoAndTempList = ufoAndTemperatureCorrelationRepository
				.findAll();
		return ufoAndTempList;
	}
}
