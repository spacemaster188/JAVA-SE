package com.javacore.controllers;

import com.javacore.containers.ArrivalStoryContainer;
import com.javacore.entities.Passenger;
import com.javacore.interfaces.Container;
import com.javacore.utils.PassengerConditions;
import com.javacore.utils.CurrentWay;
/** Service class with passemger's tasks */
public class TransportationTask extends Thread {
private PassengerController passControl;
private Thread myThread;
private Passenger passenger;
private volatile int currentFloor;
private volatile Enum<CurrentWay> currWay;
private int startFloor;
private int endFloor;
private volatile int getOutFloorFlag;
private volatile int onboardFloorFlag;
private volatile Container container;
private volatile boolean getOut;
private volatile boolean enter;
private volatile boolean isAborted;

public TransportationTask(final Passenger passenger, final PassengerController passControl) {
    super();
    this.passControl = passControl;
    this.getOut = false;
    this.passenger = passenger;
    this.startFloor = passenger.getStartFloor();
    this.endFloor = passenger.getEndFloor();
    this.isAborted = false;
    this.getOutFloorFlag = 0;
    this.onboardFloorFlag = 0;
    myThread = new Thread(this);
}

public final Thread getMyThread() {
    return myThread;
}

public final void setAborted() {
    this.isAborted = true;
}

public final Passenger getPassenger() {
    return passenger;
}

public final void setPassenger(final Passenger passenger) {
    this.passenger = passenger;
}

@Override
public final void run() {
    synchronized (this) {
        try {
            wait();
            } catch (InterruptedException e) { }
        }
    boolean flag = true;
    while (flag) {
        checkIsEnterNotify();
        checkIsGetOutNotify();
        if (checkIsAbortedState()) {
            flag = false;
        }
        if (checkIsCompletedState()) {
            flag = false;
        }
        passControl.notifyPassengerController();
        synchronized (this) {
            try {
                wait();
            } catch (Exception e) {
            }
        }
    }
}

private boolean checkIsAbortedState() {
    if (isAborted) {
        passenger.setTransportationState(PassengerConditions.ABORTED);
        return true;
    }
   return false;
}

public boolean isAborted() {
	return isAborted;
}

private boolean checkIsCompletedState() {
    if (container instanceof ArrivalStoryContainer) {
        passenger.setTransportationState(PassengerConditions.COMPLETED);
        return true;
    }
    return false;
}

private void checkIsEnterNotify() {
    if (enter && isReadyToEnter()) {
        onboardFloorFlag = startFloor;
        enter = false;
    }
}

private boolean checkIsGetOutNotify() {
    if (getOut && isReadyToGetOut()) {
        getOutFloorFlag = endFloor;
        getOut = false;
        return true;
    }
    return false;
}

public final boolean isReadyToEnter() {
    if (endFloor > currentFloor && currWay.equals(CurrentWay.UP)
        || endFloor < currentFloor && currWay.equals(CurrentWay.DOWN)) {
        return true;
    }
    return false;
}

public final boolean isReadyToGetOut() {
    if (endFloor == currentFloor) {
        return true;
    }
    return false;
}

public final int getCurrentFloor() {
    return currentFloor;
}

public final void setCurrentFloor(final int currentFloor) {
    this.currentFloor = currentFloor;
}

public final Enum<CurrentWay> getCurrWay() {
    return currWay;
}

public final void setCurrWay(Enum<CurrentWay> currWay) {
    this.currWay = currWay;
}

public final int getEndFloor() {
    return endFloor;
}

public final void setEndFloor(int endFloor) {
    this.endFloor = endFloor;
}

public final boolean isGetOut() {
    return getOut;
}

public final void setGetOut(final boolean getOut) {
    this.getOut = getOut;
}

public final boolean isEnter() {
    return enter;
}

public final void setEnter(boolean enter) {
    this.enter = enter;
}

public final void startThread() {
    myThread.start();
}

public final void notifyTransportationTask() {
    if (myThread.isAlive()) {
        synchronized (this) {
            notify();
        }
    }
}

public final Container getContainer() {
    return container;
}

public final void setContainer(final Container container) {
    this.container = container;
}

public final int getStartFloor() {
    return startFloor;
}

public final void setStartFloor(final int startFloor) {
    this.startFloor = startFloor;
}

public final int getGetOutFloorFlag() {
    return getOutFloorFlag;
}

public final void setGetOutFloorFlag(final int getOutFloorFlag) {
    this.getOutFloorFlag = getOutFloorFlag;
}

public final int getOnboardFloorFlag() {
    return onboardFloorFlag;
}

public final void setOnboardFloorFlag(final int onboardFloorFlag) {
    this.onboardFloorFlag = onboardFloorFlag;
}

}
