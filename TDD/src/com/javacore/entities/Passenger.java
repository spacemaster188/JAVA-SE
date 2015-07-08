package com.javacore.entities;

public class Passenger {
private int passengerID;
public static int currentPassengersCount = 0;
private int startFloor;
private int endFloor;

public Passenger(int floorNumber) {
	super();
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

}
