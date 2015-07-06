package com.javacore.containers;

import java.util.ArrayList;
import java.util.List;

import com.javacore.entities.Passenger;
import com.javacore.interfaces.Container;

public class ElevatorContainer implements Container {
private int elevatorCapacity;
private List <Passenger> passengerLst;

public ElevatorContainer(int elevatorCapacity) {
	super();
	this.elevatorCapacity = elevatorCapacity;
	this.passengerLst = new ArrayList<Passenger>();
}

public void addPassenger(Passenger passenger) {
	passengerLst.add(passenger);
}

public List<Passenger> getPassengerLst() {
	return passengerLst;
}

public List<Passenger> notifyPassengers(int currentFloor) {
	List<Passenger> passengerGetOutLst = new ArrayList<Passenger>();
	for (Passenger passenger : passengerLst) {
		if(passenger.isReadyGetOut(currentFloor)){
       	 passengerGetOutLst.add(passenger);
		}
	}
	for (Passenger passenger : passengerGetOutLst) {
    	passengerLst.remove(passenger);
	}
	return passengerGetOutLst;
}

public boolean isElevatorVacant(){
	if(passengerLst.size() >= elevatorCapacity){
		return false;
	}
	return true;
}

}
