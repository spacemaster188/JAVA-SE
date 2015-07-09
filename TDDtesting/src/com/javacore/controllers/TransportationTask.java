package com.javacore.controllers;

import com.javacore.containers.ArrivalStoryContainer;
import com.javacore.entities.Passenger;
import com.javacore.interfaces.Container;
import com.javacore.utils.PassengerConditions;
import com.javacore.utils.currentWay;

public class TransportationTask extends Thread {
private Thread myThread;
private Passenger passenger;
private volatile int currentFloor;
private volatile Enum<currentWay> currWay;
private int startFloor = 2;
private int endFloor;
private volatile int getOutFloorFlag;
private volatile int onboardFloorFlag;
private volatile Container container;
private volatile boolean getOut;
private volatile boolean enter;
private volatile boolean isAborted;

public TransportationTask(Passenger passenger) {
	super();
	this.isAborted = false;
	this.getOutFloorFlag = 0;
	this.onboardFloorFlag = 0;
	myThread = new Thread(this);
}

{
	passenger = new Passenger();
	container = new ArrivalStoryContainer(4);
	endFloor = 10;
	currentFloor = 2;
	currWay = currentWay.UP;
	getOut = true;
	enter = true;
}

public boolean checkChangePassengerConditionFromAnotherThread(){
	ArrivalStoryContainer ar  = new ArrivalStoryContainer(5);
	container = ar;
	run();
	if(passenger.getTransportationState().equals(PassengerConditions.COMPLETED)){
		return true;
	}
	return false;
}

public void run() {
	boolean flag = true;
	while(flag){
		checkIsEnterNotify();
        checkIsGetOutNotify();
		if(checkIsAbortedState()){
			flag = false;
		}
		if(checkIsCompletedState()){
			flag = false;
		}
	}
}

public Thread getMyThread() {
	return myThread;
}

public void setAborted() {
	this.isAborted = true;
}

public Passenger getPassenger() {
	return passenger;
}

public void setPassenger(Passenger passenger) {
	this.passenger = passenger;
}

private boolean checkIsAbortedState() {
	if(isAborted) {
		passenger.setTransportationState(PassengerConditions.ABORTED);
    	System.out.println("Passenger's state was setted by Thread as ABORTED!!!");
    	return true;
    }
	return false;
}

public boolean checkIsCompletedState() {
	if (container instanceof ArrivalStoryContainer) {
		passenger.setTransportationState(PassengerConditions.COMPLETED);
	}
	boolean out;
    return out = passenger.getTransportationState().equals(PassengerConditions.COMPLETED)?true:false;
}

private void checkIsEnterNotify() {
	if(enter && isReadyToEnter()){
		onboardFloorFlag = startFloor;
		enter = false;
	}
}

public boolean checkIsGetOutNotify() {
	if(getOut && isReadyToGetOut()){
		getOutFloorFlag = endFloor;
		getOut = false;
		return true;
	}
	return false;
}

public boolean isReadyToEnter() {
	if(endFloor > currentFloor && currWay.equals(currentWay.UP) || endFloor < currentFloor && currWay.equals(currentWay.DOWN)){
		return true;
	}
	return false;
}

public boolean isReadyToGetOut() {
	if(endFloor == currentFloor){
		return true;
	}
	return false;
}

public int getCurrentFloor() {
	return currentFloor;
}

public void setCurrentFloor(int currentFloor) {
	this.currentFloor = currentFloor;
}

public Enum<currentWay> getCurrWay() {
	return currWay;
}

public void setCurrWay(Enum<currentWay> currWay) {
	this.currWay = currWay;
}

public int getEndFloor() {
	return endFloor;
}

public void setEndFloor(int endFloor) {
	this.endFloor = endFloor;
}

public boolean isGetOut() {
	return getOut;
}

public void setGetOut(boolean getOut) {
	this.getOut = getOut;
}

public boolean isEnter() {
	return enter;
}

public void setEnter(boolean enter) {
	this.enter = enter;
}

public void startThread() {
	myThread.start();
}

public void notifyTransportationTask() {
	if(myThread.isAlive()){
		synchronized (this) { 
			notify();
		}
	}
}

public Container getContainer() {
	return container;
}

public void setContainer(Container container) {
	this.container = container;
}

public int getStartFloor() {
	return startFloor;
}

public void setStartFloor(int startFloor) {
	this.startFloor = startFloor;
}

public int getGetOutFloorFlag() {
	return getOutFloorFlag;
}

public void setGetOutFloorFlag(int getOutFloorFlag) {
	this.getOutFloorFlag = getOutFloorFlag;
}

public int getOnboardFloorFlag() {
	return onboardFloorFlag;
}

public void setOnboardFloorFlag(int onboardFloorFlag) {
	this.onboardFloorFlag = onboardFloorFlag;
}

}
