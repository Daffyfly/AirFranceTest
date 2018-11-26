package com.meritis.airfrance.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller Advice for the UserNotAllowedException
 * @author vraybaud
 *
 */
@ControllerAdvice
public class UserNotAllowedAdvice {

	/**
	 * The Handler for the exception
	 * @param ex
	 * @return The error message
	 */
	@ResponseBody
	@ExceptionHandler(UserNotAllowedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	String employeeNotAllowedHandler(UserNotAllowedException ex) {
		return ex.getMessage();
	}
}