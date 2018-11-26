package com.meritis.airfrance.service;

import com.meritis.airfrance.model.AirFranceUser;

/**
 * AirFranceService the interface of the AirFranceApp Database Service
 * @author vraybaud
 *
 */
public interface AirFranceService {

	/**
	 * Find the user by his id
	 * @param id
	 * @return
	 */
	AirFranceUser findById(long id);
	
	/**
	 * Add a user
	 * @param user
	 */
	void add(AirFranceUser user);
}
