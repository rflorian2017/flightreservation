package com.rosu.flights.model;

//pojo
public class PassengerModel {
	
	
	private long id;

	private String firstName;
	
	private String lastName;
	
	private int age;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public PassengerModel() {
		
	}
	
	public Passenger translateModelToPassenger() {
		Passenger passenger = new Passenger();
		passenger.setAge(this.getAge());
		passenger.setFirstName(this.getFirstName());
		passenger.setLastName(this.getLastName());
		passenger.setId(this.getId());
		return passenger;
	}
	
	

}
