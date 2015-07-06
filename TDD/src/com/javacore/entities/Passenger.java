package com.javacore.entities;
import com.javacore.containers.DispatchStoryContainer;
import com.javacore.interfaces.Container;
import com.javacore.utils.PassengerConditions;
import com.javacore.utils.currentWay;

public class Passenger {
private Enum<PassengerConditions> transportationState;
private int passengerID;
public static int currentPassengersCount = 0;
private int startFloor;
private int endFloor;
private volatile Container container;

public Passenger(int floorNumber) {
	super();
	this.transportationState = PassengerConditions.NOT_STARTED;
	this.passengerID = currentPassengersCount++;
	this.startFloor = getRandomStartFloor(floorNumber);
	this.container = new DispatchStoryContainer(startFloor);
	this.endFloor = getRandomEndFloor(floorNumber);
}

public Container getContainer() {
	return container;
}

public void setContainer(Container container) {
	this.container = container;
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

public boolean isReadyGetOut(int currentFloor) {
	if(endFloor == currentFloor){
		return true;
	}
	return false;
}

public boolean isReadyToGo(int currentFloor, Enum<currentWay> currWay) {
	if(endFloor > currentFloor && currWay.equals(currentWay.UP) || endFloor < currentFloor && currWay.equals(currentWay.DOWN)){
		return true;
	}
	return false;
}

@Override
public String toString() {
	return "Passenger [transportationState=" + transportationState
			+ ", passengerID=" + passengerID + ", startFloor=" + startFloor + ", endFloor=" + endFloor + "]";
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
