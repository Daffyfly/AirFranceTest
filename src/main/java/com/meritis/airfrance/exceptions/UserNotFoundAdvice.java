package com.meritis.airfrance.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller Advice for the User Not Found Exception
 * @author vraybaud
 *
 */
@ControllerAdvice
public class UserNotFoundAdvice {

	/**
	 * Handler for the error
	 * @param ex
	 * @return the error message
	 */
	@ResponseBody
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String employeeNotFoundHandler(UserNotFoundException ex) {
		return ex.getMessage();
	}
}