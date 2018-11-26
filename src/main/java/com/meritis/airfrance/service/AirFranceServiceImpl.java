package com.meritis.airfrance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meritis.airfrance.model.AirFranceUser;
import com.meritis.airfrance.repository.AirFranceRepository;

/**
 * Implementation of the AirFranceService interface
 * @author vraybaud
 *
 */
@Service
public class AirFranceServiceImpl implements AirFranceService {

	@Autowired
	AirFranceRepository repository;

	public AirFranceUser findById(long id) {
		return repository.findById(id);
	}
	
	public void add(AirFranceUser user) {
		repository.add(user);
	}
}