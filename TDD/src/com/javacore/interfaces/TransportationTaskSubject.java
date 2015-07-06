package com.javacore.interfaces;

import com.javacore.entities.Passenger;

public interface TransportationTaskSubject {
	
	public void register(TransportationTaskObserver o);
	public void removeObserverByPassenger(Passenger passenger);
	public void notifyObservers(Passenger passenger);
	public void notifyObservers();
	
}
