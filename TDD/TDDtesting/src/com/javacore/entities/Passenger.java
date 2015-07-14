package com.javacore.entities;

import com.javacore.utils.PassengerConditions;

public class Passenger {
private int passengerID;
public static int currentPassengersCount = 0;
private int startFloor;
private int endFloor;
private Enum<PassengerConditions> transportationState;

public Passenger() {
	super();
	this.transportationState = PassengerConditions.NOT_STARTED;
	this.passengerID = currentPassengersCount++;
}

public boolean getRandomStartFloor(int floorNumber) {
	return isCorrectRandomGeneration(floorNumber, (int)(Math.random()*floorNumber + 1));
}

public boolean getRandomEndFloor(int floorNumber) {
	int outFloor = startFloor;
	while(outFloor == startFloor){
		outFloor = (int)(Math.random()*floorNumber + 1);
	}
	return isCorrectRandomGeneration(floorNumber, outFloor);
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

public boolean isCorrectRandomGeneration(int floorNumber, int generatedValue) {
	if(generatedValue <= floorNumber && generatedValue >= 1){
		return true;
	}
	return false;
}

public Enum<PassengerConditions> getTransportationState() {
	return transportationState;
}

public void setTransportationState(Enum<PassengerConditions> transportationState) {
	this.transportationState = transportationState;
}

public int getPassengerID() {
	return passengerID;
}

public void setPassengerID(int passengerID) {
	this.passengerID = passengerID;
}

}
