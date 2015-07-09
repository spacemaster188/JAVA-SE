package com.javacore.entities;

import com.javacore.utils.PassengerConditions;

/** Passenger entity */
public class Passenger {
private int passengerID;
public static int currentPassengersCount = 0;
private int startFloor;
private int endFloor;
private Enum<PassengerConditions> transportationState;

public Passenger(int floorNumber) {
	super();
	this.transportationState = PassengerConditions.NOT_STARTED;
	this.passengerID = currentPassengersCount++;
	this.startFloor = getRandomStartFloor(floorNumber);
	this.endFloor = getRandomEndFloor(floorNumber);
}

public int getRandomStartFloor(int floorNumber) {
	return (int)(Math.random()*floorNumber + 1);
}

public int getRandomEndFloor(int floorNumber) {
	int outFloor = startFloor;
	while(outFloor == startFloor){
		outFloor = (int)(Math.random()*floorNumber + 1);
	}
	return outFloor;
}

public int getPassengerID() {
	return passengerID;
}

public void setPassengerID(int passengerID) {
	this.passengerID = passengerID;
}

public int getStartFloor() {
	return startFloor;
}

public void setStartFloor(int startFloor) {
	this.startFloor = startFloor;
}

public int getEndFloor() {
	return endFloor;
}

public void setEndFloor(int endFloor) {
	this.endFloor = endFloor;
}

public Enum<PassengerConditions> getTransportationState() {
	return transportationState;
}

public void setTransportationState(Enum<PassengerConditions> transportationState) {
	this.transportationState = transportationState;
}

}
