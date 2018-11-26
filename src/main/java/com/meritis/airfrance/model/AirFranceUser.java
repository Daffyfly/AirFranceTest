package com.meritis.airfrance.model;

/**
 * AirFranceUser
 * @author vraybaud
 *
 */
public class AirFranceUser {

    private final long id;
    private final String name;
    private  final int age;
    private final String location;
    private final String hobby;
    
    /**
     * Constructor for AirFranceUser
     * @param id
     * @param name
     * @param age
     * @param location
     * @param hobby
     */
	public AirFranceUser(long id, String name, int age, String location, String hobby) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.location = location;
		this.hobby = hobby;
	}
	
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}
	
	public String getLocation() {
		return location;
	}
	
	public String getHobby() {
		return hobby;
	}
	
	@Override
    public String toString() {
        return "{\"id\":" + id + ",\"name\":\"" + name + "\",\"age\":" + age + ",\"location\":\"" + location + "\",\"hobby\":\"" + hobby + "\"}";
    }

}