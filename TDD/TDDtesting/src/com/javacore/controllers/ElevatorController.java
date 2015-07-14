package com.javacore.controllers;
import com.javacore.utils.Constants;
import com.javacore.utils.currentWay;

public class ElevatorController {
private int floorNumber;
private int currentFloor;
private Enum <currentWay> currWay;
boolean runFlag;
boolean isTerminate;
private int moveDelay;
final int DELAY = 1000;
final int floorLength = 300;
final int speedPerSec = 100;

public ElevatorController() throws InterruptedException {
	super();
	this.floorNumber = 6;
	this.currentFloor = 3;
	this.currWay = currentWay.UP;
	this.runFlag = true;
	this.isTerminate = false;
	this.moveDelay = 1;
}

public int runElevatorGetFloor() throws InterruptedException{
	int currentLength = 0;
	System.out.println(Constants.STARTING_TRANSPORTATION);
	while(runFlag && !isTerminate){
		if(currentFloor == 2){
			return currentFloor;
		}
		if(currentLength == 0) {
		    System.out.println(Constants.MOVING_ELEVATOR + currentFloor + Constants.TO_FLOOR + getDestinationFloor() + Constants.CLOSE_STR);
		}
		currentLength += 100;
		if(currentLength==300){
          if(currWay.equals(currentWay.UP)){
        	  currentFloor++;
        	  currentLength = 0;
          }else{
        	  currentFloor--;
        	  currentLength = 0;
          }
		}
		/** Check for upper floor */
		if(currentFloor == floorNumber && currWay.equals(currentWay.UP)){
			currWay = currentWay.DOWN;
		}
		/** Check for lower floor */
		if(currentFloor == 1 && currWay.equals(currentWay.DOWN)){
			currWay = currentWay.UP;
		}
		Thread.currentThread();
		Thread.sleep(moveDelay);
	}
	if(isTerminate){
		System.out.println(Constants.ABORTING_TRANSPORTATION);
	}
	return currentFloor;
}

public Enum<currentWay> runElevatorGetWay() throws InterruptedException{
	int currentLength = 0;
	System.out.println(Constants.STARTING_TRANSPORTATION);
	while(runFlag && !isTerminate){
		if(currentFloor == 2){
			return currWay;
		}
		if(currentLength == 0) {
		    System.out.println(Constants.MOVING_ELEVATOR + currentFloor + Constants.TO_FLOOR + getDestinationFloor() + Constants.CLOSE_STR);
		}
		currentLength += 100;
		if(currentLength==300){
          if(currWay.equals(currentWay.UP)){
        	  currentFloor++;
        	  currentLength = 0;
          }else{
        	  currentFloor--;
        	  currentLength = 0;
          }
		}
		/** Check for upper floor */
		if(currentFloor == floorNumber && currWay.equals(currentWay.UP)){
			currWay = currentWay.DOWN;
		}
		/** Check for lower floor */
		if(currentFloor == 1 && currWay.equals(currentWay.DOWN)){
			currWay = currentWay.UP;
		}
		Thread.currentThread();
		Thread.sleep(moveDelay);
	}
	if(isTerminate){
		System.out.println(Constants.ABORTING_TRANSPORTATION);
	}
	return currWay;
}

public int runElevatorFloorCount() throws InterruptedException{
	int currentLength = 0;
	int chkFloorCountTmp = 0;
	System.out.println(Constants.STARTING_TRANSPORTATION);
	while(runFlag && !isTerminate){
		if(currentFloor == 2 && currWay.equals(currentWay.UP)){
			return chkFloorCountTmp;
		}
		if(currentLength == 0) {
		    System.out.println(Constants.MOVING_ELEVATOR + currentFloor + Constants.TO_FLOOR + getDestinationFloor() + Constants.CLOSE_STR);
		}
		currentLength += 100;
		if(currentLength==300){
		  chkFloorCountTmp++;
          if(currWay.equals(currentWay.UP)){
        	  currentFloor++;
        	  currentLength = 0;
          }else{
        	  currentFloor--;
        	  currentLength = 0;
          }
		}
		/** Check for upper floor */
		if(currentFloor == floorNumber && currWay.equals(currentWay.UP)){
			currWay = currentWay.DOWN;
		}
		/** Check for lower floor */
		if(currentFloor == 1 && currWay.equals(currentWay.DOWN)){
			currWay = currentWay.UP;
		}
		Thread.currentThread();
		Thread.sleep(moveDelay);
	}
	if(isTerminate){
		System.out.println(Constants.ABORTING_TRANSPORTATION);
	}
	return chkFloorCountTmp;
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

}
