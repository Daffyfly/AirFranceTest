package com.meritis.airfrance.exceptions;

import com.meritis.airfrance.model.AirFranceUser;

/**
 * UserNotAllowedException when a user can't create an account
 * @author vraybaud
 *
 */
public class UserNotAllowedException extends RuntimeException {

	/**
	 * Constructor
	 * @param user
	 */
	public UserNotAllowedException(AirFranceUser user) {
		super("Could not create user " + user.toString());
	}
}