package com.meritis.airfrance;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.meritis.airfrance.exceptions.UserNotAllowedException;
import com.meritis.airfrance.model.AirFranceUser;
import com.meritis.airfrance.service.AirFranceService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * AirFranceAppController
 * Rest Controller for Air France Application
 * @author vraybaud
 *
 */
@RestController
public class AirFranceAppController {

	@Autowired
	AirFranceService service;
	
	public final static String CREATE_URL = "/create";
	public final static String FIND_URL = "/find/{id}";
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
    private final AtomicLong counter = new AtomicLong();

    /**
     * Request Mapping for create user route generating Id automatically
     * 
     * @param name Name of User
     * @param age Age of User > 18
     * @param location Location of User == France
     * @param hobby Hobby of user, not required and default value
     */
    @RequestMapping(value = CREATE_URL, method = RequestMethod.POST)
    public void createUser(@RequestParam(value="name", required = true) String name,
    							@RequestParam(value = "age") Integer age,
    							@RequestParam(value = "location") String location,
    							@RequestParam(value = "hobby", required = false, defaultValue = "none") String hobby) {
    	Instant start = Instant.now();
    	AirFranceUser user = new AirFranceUser(counter.incrementAndGet(),
								        		name, 
								        		age,
								        		location,
								        		hobby);
		if(user.getAge() < 18 || !user.getLocation().equals("France")) {
			counter.decrementAndGet();
			System.out.println("Can't create user");
			throw new UserNotAllowedException(user);
		}
		else {
			log.info("Created User: " + user.toString());
			service.add(user);
		}
		Instant end = Instant.now();
		log.info("Time spent to do the request: " + Duration.between(start, end).toNanos() + " nanoseconds.");
        
    }
    
    /**
     * Request Mapping for finding a user by id
     * 
     * @param id
     * @return String describing the user found
     */
    @RequestMapping(FIND_URL)
    @ResponseBody
    public AirFranceUser findUserById(@PathVariable("id") long id) {
    	Instant start = Instant.now();
    	log.info("Searching user with id: " + id);
        AirFranceUser user = service.findById(id);
        Instant end = Instant.now();
		log.info("Time spent to do the request: " + Duration.between(start, end).toNanos() + " nanoseconds.");
		return user;
    }
}