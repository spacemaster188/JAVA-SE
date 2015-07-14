package com.javacore.controllers;
/** Service controller for elevator work */
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.javacore.gui.ElevatorGUI;
import com.javacore.interfaces.ElevatorObserver;
import com.javacore.interfaces.ElevatorSubject;
import com.javacore.utils.Constants;
import com.javacore.utils.GlobalLog;
import com.javacore.utils.LoggingConstants;
import com.javacore.utils.Properties;
import com.javacore.utils.CurrentWay;

public class ElevatorController implements ElevatorSubject {
private ArrayList<ElevatorObserver> elevatorObservers;
private PassengerController passengersController;
private int floorNumber;
private int currentFloor;
private Enum<CurrentWay> currWay;
private boolean isTerminate;
private boolean isComplete;
private int animationBoost;
private int moveDelay;
private final int delay = 1000;
private final int floorLength = 300;
private final int speedPerSec = 100;
static final Logger logger = Logger.getLogger("ElevatorController.class");

public ElevatorController(PassengerController passContr) throws InterruptedException {
    super();
    elevatorObservers = new ArrayList<ElevatorObserver>();
    this.animationBoost = Properties.getAnimationBoost();
    this.passengersController = passContr;
    this.floorNumber = Properties.getFloorNumber();
    this.currentFloor = 1;
    this.currWay = CurrentWay.UP;
    this.isTerminate = false;
    this.isComplete = false;
    if (animationBoost != 0) {
        this.moveDelay = delay / Properties.getAnimationBoost();
    } else {
        this.moveDelay = delay / 1;
    }
}

public final void start() throws InterruptedException {
    if (animationBoost == 0) {
        runElevator();
    } else {
        ElevatorGUI gui = new ElevatorGUI(this);
        register(gui);
    }
}
public void runElevator() throws InterruptedException {
    int currentLength = 0;
    logger.info(LoggingConstants.STARTING_TRANSPORTATION.getConstant());
    while (!isTerminate) {
        /** Check for boarding and deborning passengers */
        if (currentLength == 0) {
            System.out.println("ELEVATOR CAME ON FLOOR: " + currentFloor);
            notifyElevatorPassengersGetOut();
            getOutPassengers();
            notifyPassengersToEnterElevator();
            takeOnboardPassengers();
            /** Get conditions for elevator stopping  */
            if (passengersController.hasNoTransportationTasks()) {
                logger.info(LoggingConstants.COMPLETION_TRANSPORTATION);
                isComplete = true;
                notifyObservers();
                passengersController.isConditionsStoppingElevatoraVerified();
                notifyObservers();
                break;
            } else {
                logger.info(GlobalLog.addLog(Constants.MOVING_ELEVATOR
                        + currentFloor + Constants.TO_FLOOR + getDestinationFloor()
                        + Constants.CLOSE_STR));
            }
        }
        currentLength += speedPerSec;
        if (currentLength == floorLength) {
            if (currWay.equals(CurrentWay.UP)) {
                currentFloor++;
                currentLength = 0;
            } else {
                currentFloor--;
                currentLength = 0;
            }
          notifyObservers();
        }
        /** Check for upper floor */
        if (currentFloor == floorNumber && currWay.equals(CurrentWay.UP)) {
            currWay = CurrentWay.DOWN;
            notifyObservers();
        }
        /** Check for lower floor */
        if (currentFloor == 1 && currWay.equals(CurrentWay.DOWN)) {
            currWay = CurrentWay.UP;
            notifyObservers();
        }
        Thread.currentThread();
        Thread.sleep(moveDelay);
    }
    if (isTerminate) {
        logger.info(LoggingConstants.ABORTING_TRANSPORTATION.getConstant());
        terminateElevatorProcess();
        notifyObservers();
        logger.info(LoggingConstants.INTERRUPTED_STATE.getConstant());
    }
}

private int getDestinationFloor() {
    if (currWay.equals(CurrentWay.UP)) {
        if (currentFloor == floorNumber) {
            return currentFloor - 1;
        }
        return currentFloor + 1;
    }
    if (currentFloor == 1) {
        return currentFloor + 1;
    }
    return currentFloor - 1;
}

private void notifyElevatorPassengersGetOut() {
    passengersController.notifyElevatorPassengersGetOut(currentFloor, currWay);
}

private void notifyPassengersToEnterElevator(){
    passengersController.notifyIncomingPassengersToEnter(currentFloor, currWay);
}

private void getOutPassengers(){
    passengersController.getOutPassengers(currentFloor);
}

private void takeOnboardPassengers() {
    passengersController.takeOnboardPassengers(currentFloor);
}

private void terminateElevatorProcess(){
    passengersController.terminateTransportationTasks();
}

public final int getCurrentFloor() {
    return currentFloor;
}

public final Enum<CurrentWay> getCurrWay() {
    return currWay;
}

public final boolean isTerminate() {
    return isTerminate;
}

public final void setTerminate() {
    this.isTerminate = true;
}

public final void setComplete(final boolean isComplete) {
    this.isComplete = isComplete;
}

public final boolean isComplete() {
    return isComplete;
}

public final int getAnimationBoost() {
    return animationBoost;
}

@Override
public final void register(final ElevatorObserver newObserver) {
    elevatorObservers.add(newObserver);
}

@Override
public final void notifyObservers() {
    for (ElevatorObserver elevatorObserver : elevatorObservers) {
        elevatorObserver.update();
    }
}

}
