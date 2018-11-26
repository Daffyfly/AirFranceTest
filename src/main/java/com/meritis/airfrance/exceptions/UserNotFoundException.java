package com.meritis.airfrance.exceptions;

/**
 * UserNotFoundException when a user with the corresponding id doesn't exist
 * @author vraybaud
 *
 */
public class UserNotFoundException extends RuntimeException {

	/**
	 * Constructor 
	 * @param id
	 */
	public UserNotFoundException(Long id) {
		super("Could not find user " + id);
	}
}