package com.javacore.containers;

import java.util.ArrayList;
import java.util.List;

import com.javacore.entities.Passenger;
import com.javacore.interfaces.Container;
import com.javacore.utils.currentWay;

public class DispatchStoryContainer implements Container {
private int floor;
private List <Passenger> passengerLst;

public DispatchStoryContainer(int floor) {
	super();
	this.floor = floor;
	passengerLst = new ArrayList<Passenger>();
}

public int getFloor() {
	return floor;
}

public void addPassenger(Passenger passenger) {
	passengerLst.add(passenger);
}

public List<Passenger> getPassengerLst() {
	return passengerLst;
}

public List<Passenger> notifyPassengers(Enum<currentWay> currWay) {
	List<Passenger> passengerIncomingLst = new ArrayList<Passenger>();
	for (Passenger passenger : passengerLst) {
		if(passenger.isReadyToGo(floor, currWay)){
       	 passengerIncomingLst.add(passenger);
		}
	}
	for (Passenger passenger : passengerIncomingLst) {
      	 passengerLst.remove(passenger);
	}
	return passengerIncomingLst;
}

}
