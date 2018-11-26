package com.meritis.airfrance.repository;

import com.meritis.airfrance.model.AirFranceUser;

/**
 * Interface for the Repository
 * @author vraybaud
 *
 */
public interface AirFranceRepository {
	/**
	 * Find a User by his id in the database
	 * @param id
	 * @return
	 */
	AirFranceUser findById(long id);
	
	/**
	 * Add a user to the database
	 * @param user
	 */
	void add(AirFranceUser user);
}

