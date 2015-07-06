package com.javacore.controllers;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.javacore.gui.ElevatorGUI;
import com.javacore.interfaces.ElevatorObserver;
import com.javacore.interfaces.ElevatorSubject;
import com.javacore.utils.Constants;
import com.javacore.utils.GlobalLog;
import com.javacore.utils.LoggingConstants;
import com.javacore.utils.Properties;
import com.javacore.utils.currentWay;

public class ElevatorController implements ElevatorSubject {
private ArrayList<ElevatorObserver> elevatorObservers;
private PassengerController passengersController;
private int floorNumber;
private int currentFloor;
private Enum <currentWay> currWay;
boolean runFlag;
boolean isTerminate;
boolean isComplete;
private int animationBoost;
private int moveDelay;
final int DELAY = 1000;
final int floorLength = 300;
final int speedPerSec = 100;
final static Logger logger = Logger.getLogger("ElevatorController.class");

public ElevatorController(PassengerController passContr) throws InterruptedException {
	super();
	elevatorObservers = new ArrayList<ElevatorObserver>();
	this.animationBoost = Properties.getAnimationBoost();
	this.passengersController = passContr;
	this.floorNumber = Properties.getFloorNumber();
	this.currentFloor = 1;
	this.currWay = currentWay.UP;
	this.isTerminate = false;
	this.isComplete = false;
	if(animationBoost != 0){
	this.moveDelay = DELAY/Properties.getAnimationBoost();	
	}else{
	this.moveDelay = DELAY/1;
	}
}

public void start() throws InterruptedException{
	if(animationBoost == 0){
		runElevator();
	} else {
		ElevatorGUI gui = new ElevatorGUI(this);
		register(gui);
	}
}
public void runElevator() throws InterruptedException{
	int currentLength = 0;
	logger.info(LoggingConstants.STARTING_TRANSPORTATION.getConstant());
	while(!isTerminate){
		/** Check for boarding and deborning passengers */
		if(currentLength == 0) {
			notifyElevatorPassengers();
			notifyIncomingPassengers();
			/** Get conditions for elevator stopping  */
			if(passengersController.hasNoTransportationTasks()){
				logger.info(LoggingConstants.COMPLETION_TRANSPORTATION);
				isComplete = true;
				notifyObservers();
				passengersController.isConditionsStoppingElevatoraVerified();
				notifyObservers();
				break;
			}else{
				logger.info(GlobalLog.addLog(Constants.MOVING_ELEVATOR + currentFloor + Constants.TO_FLOOR + getDestinationFloor() + Constants.CLOSE_STR));
			}
		}
		currentLength += speedPerSec;
		if(currentLength == floorLength){
          if(currWay.equals(currentWay.UP)){
        	  currentFloor++;
        	  currentLength = 0;
          }else{
        	  currentFloor--;
        	  currentLength = 0;
          }
          notifyObservers();
		}
		/** Check for upper floor */
		if(currentFloor == floorNumber && currWay.equals(currentWay.UP)){
			currWay = currentWay.DOWN;
			notifyObservers();
		}
		/** Check for lower floor */
		if(currentFloor == 1 && currWay.equals(currentWay.DOWN)){
			currWay = currentWay.UP;
			notifyObservers();
		}
		Thread.currentThread().sleep(moveDelay);
	}
	if(isTerminate){
		logger.info(LoggingConstants.ABORTING_TRANSPORTATION.getConstant());
		terminateElevatorProcess();
		notifyObservers();
		logger.info(LoggingConstants.INTERRUPTED_STATE.getConstant());
	}
}

private int getDestinationFloor(){
	if(currWay.equals(currentWay.UP)){
		if(currentFloor == floorNumber){
			return currentFloor - 1;
		}
		return currentFloor + 1;
	}
	if(currentFloor == 1){
		return currentFloor + 1;
	}
	return currentFloor - 1;
}

private void notifyElevatorPassengers(){
	passengersController.notifyElevatorPassengers(currentFloor);
}

private void notifyIncomingPassengers(){
	passengersController.notifyIncomingPassengers(currentFloor, currWay);
}

private void terminateElevatorProcess(){
	passengersController.terminateTransportationTasks();
}

public int getCurrentFloor() {
	return currentFloor;
}

public Enum<currentWay> getCurrWay() {
	return currWay;
}

public boolean isTerminate() {
	return isTerminate;
}

public void setTerminate() {
	this.isTerminate = true;
}

public void setComplete(boolean isComplete) {
	this.isComplete = isComplete;
}

public boolean isComplete() {
	return isComplete;
}

public int getAnimationBoost() {
	return animationBoost;
}

@Override
public void register(ElevatorObserver newObserver) {
	elevatorObservers.add(newObserver);
}

@Override
public void notifyObservers() {
	for(ElevatorObserver elevatorObserver : elevatorObservers){
		elevatorObserver.update();
	}
}

}
