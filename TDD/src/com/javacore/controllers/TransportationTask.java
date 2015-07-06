package com.javacore.controllers;

import com.javacore.containers.ArrivalStoryContainer;
import com.javacore.entities.Passenger;
import com.javacore.interfaces.TransportationTaskObserver;
import com.javacore.utils.PassengerConditions;

public class TransportationTask extends Thread implements TransportationTaskObserver {
private final Thread myThread;
private Passenger passenger;
private boolean isAborted;

public TransportationTask(Passenger passenger) {
	super();
	this.passenger = passenger;
	this.isAborted = false;
	setTransportationState(PassengerConditions.IN_PROGRESS);
	myThread = new Thread(this);
}

public Thread getMyThread() {
	return myThread;
}

public void setAborted() {
	this.isAborted = true;
}

public void setTransportationState(Enum<PassengerConditions> transportationState){
	passenger.setTransportationState(transportationState);
}

public Passenger getPassenger() {
	return passenger;
}

public void setPassenger(Passenger passenger) {
	this.passenger = passenger;
}

@Override
public void run() {
	checkIsAbortedState();
	checkIsCompletedState();
}

public void update() {
        myThread.start();
}

private void checkIsAbortedState() {
	if(isAborted){
    	passenger.setTransportationState(PassengerConditions.ABORTED);
    	System.out.println("Passenger's state was setted by Thread as ABORTED!!!");
    }
}

private void checkIsCompletedState() {
	if (passenger.getContainer() instanceof ArrivalStoryContainer) {
		passenger.setTransportationState(PassengerConditions.COMPLETED);
	}
}

}
