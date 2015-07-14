package com.javacore.interfaces;
/** Service interface for registering and notify elevator's observers */
public interface ElevatorSubject {

    public void register(ElevatorObserver o);
    public void notifyObservers();

}
