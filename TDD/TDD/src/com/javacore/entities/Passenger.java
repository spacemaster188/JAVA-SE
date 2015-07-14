package com.javacore.entities;

import com.javacore.utils.PassengerConditions;

/** Passenger entity bean*/
public class Passenger {
private int passengerID;
private static int currentPassengersCount = 0;
private int startFloor;
private int endFloor;
private Enum<PassengerConditions> transportationState;

public Passenger(final int floorNumber) {
    super();
    this.transportationState = PassengerConditions.NOT_STARTED;
    this.passengerID = currentPassengersCount++;
    this.startFloor = getRandomStartFloor(floorNumber);
    this.endFloor = getRandomEndFloor(floorNumber);
}

public final int getRandomStartFloor(final int floorNumber) {
    return (int) (Math.random() * floorNumber + 1);
}

public final int getRandomEndFloor(final int floorNumber) {
    int outFloor = startFloor;
    while (outFloor == startFloor) {
        outFloor = (int) (Math.random() * floorNumber + 1);
    }
    return outFloor;
}

public final int getPassengerID() {
    return passengerID;
}

public final void setPassengerID(final int passengerID) {
    this.passengerID = passengerID;
}

public final int getStartFloor() {
    return startFloor;
}

public final void setStartFloor(final int startFloor) {
    this.startFloor = startFloor;
}

public final int getEndFloor() {
    return endFloor;
}

public final void setEndFloor(final int endFloor) {
    this.endFloor = endFloor;
}

public final Enum<PassengerConditions> getTransportationState() {
    return transportationState;
}

public final void setTransportationState(final Enum<PassengerConditions> transportationState) {
    this.transportationState = transportationState;
}

}
