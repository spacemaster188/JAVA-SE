package com.javacore.containers;

import java.util.ArrayList;
import java.util.List;

import com.javacore.entities.Passenger;
import com.javacore.interfaces.Container;

public class ArrivalStoryContainer implements Container {
	private int floor;
	private List <Passenger> passengerLst;
	
	public ArrivalStoryContainer(int floor) {
		super();
		this.floor = floor;
		passengerLst = new ArrayList<Passenger>();
	}
	
	public void addPassenger(Passenger passenger) {
		passenger.setContainer(this);
		passengerLst.add(passenger);
	}

	public int getFloor() {
		return floor;
	}

	public List<Passenger> getPassengerLst() {
		return passengerLst;
	}
	
}
