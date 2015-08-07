package com.javacore.controllers;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;

import org.junit.Test;

import com.javacore.entities.Passenger;
import com.javacore.utils.CurrentWay;

public class PassengerControllerTest3 {

	@Test
	public void fillFloorByPassengerAndNotifyFloorPassengerToComingElevatorGoUp() throws IOException {
	final int ANY_BUILDING_FLOOR_COUNT = 11;
	final int START_PASSENGER_FLOOR = 1;
	final int END_PASSENGER_FLOOR = 2;
	PassengerController passengerController = new PassengerController();
	Passenger passenger = new Passenger(ANY_BUILDING_FLOOR_COUNT);
	passenger.setStartFloor(START_PASSENGER_FLOOR);
	passenger.setEndFloor(END_PASSENGER_FLOOR);
	TransportationTask transportationTask = new TransportationTask(passenger, passengerController);
	
	assertThat("Before notify passenger to go UP their state to enter elevator must be inactive :",
			transportationTask.isEnter(), is(false));
	transportationTask.setCurrentFloor(START_PASSENGER_FLOOR);
	transportationTask.setCurrWay(CurrentWay.UP);
	transportationTask.setEnter(true);
		assertThat("After notify passenger he must be ready to enter elevator :",
				transportationTask.isReadyToEnter() && transportationTask.isEnter(), is(true));
	}


}
