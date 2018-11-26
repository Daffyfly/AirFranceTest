package com.meritis.airfrance.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.meritis.airfrance.exceptions.UserNotAllowedException;
import com.meritis.airfrance.exceptions.UserNotFoundException;
import com.meritis.airfrance.model.AirFranceUser;

/**
 * Implementation of the AirFranceRepository for Collection database with no persistance
 * @author vraybaud
 *
 */
@Repository
public class AirFranceRepositoryImpl implements AirFranceRepository {

	List<AirFranceUser> users = new ArrayList<>();
	
	@Override
	public AirFranceUser findById(long id){
		
		return this.users.stream()
				.filter(user -> user.getId() == id)
				.findFirst().orElseThrow(() -> new UserNotFoundException(id));
	}

	@Override
	public void add(AirFranceUser user) {
		this.users.add(user);
	}
	
}
