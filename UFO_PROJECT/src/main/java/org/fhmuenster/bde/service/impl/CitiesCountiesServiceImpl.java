package org.fhmuenster.bde.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.fhmuenster.bde.entity.CitiesCounties;
import org.fhmuenster.bde.repository.CitiesCountiesRepository;
import org.fhmuenster.bde.service.CitiesCountiesService;
import org.springframework.stereotype.Service;

@Service
public class CitiesCountiesServiceImpl implements CitiesCountiesService {

	@Inject
	CitiesCountiesRepository citiesCoutiesRepository;

	@Override
	public void findAll() {
		List<CitiesCounties> citiesCounties = citiesCoutiesRepository.findAll();
		System.out.println("Number of citiesCounties = "
				+ citiesCounties.size());
		System.out.println(citiesCounties);
	}
}
