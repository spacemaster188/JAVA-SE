package com.javacore.interfaces;

public interface ElevatorSubject {

	public void register(ElevatorObserver o);
	public void notifyObservers();
	
}
