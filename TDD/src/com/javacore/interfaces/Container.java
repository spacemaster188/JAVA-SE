package com.javacore.interfaces;

import java.util.List;

import com.javacore.entities.Passenger;

public interface Container {
	
	public void addPassenger(Passenger passenger);
	
	public List<Passenger> getPassengerLst();
}
