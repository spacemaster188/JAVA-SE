package com.javacore.controllers;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;

import org.junit.Test;

import com.javacore.containers.ArrivalStoryContainer;
import com.javacore.entities.Passenger;
import com.javacore.utils.PassengerConditions;

public class PassengerControllerTest4 {

	@Test
	public void fillArrivalStoryContainerByPassengerAndCheckIsArrivedForFinalServiceChecking()
			throws IOException {
		final int END_FLOOR = 11;
		PassengerController passengerController = new PassengerController();
		Passenger passenger = new Passenger(12);
		TransportationTask transportationTask = new TransportationTask(
				passenger, passengerController);
		ArrivalStoryContainer container = new ArrivalStoryContainer(END_FLOOR);
		container.getTransportationTaskLst().add(transportationTask);
		passengerController.getArrivStorContLst().add(container);

		assertThat(
				"Passenger's condition is not COMPLETED and validation should fail :",
				passengerController.isArrivedPassengersVerified(), is(false));

		passenger.setTransportationState(PassengerConditions.COMPLETED);
		transportationTask.setEndFloor(END_FLOOR);

		assertThat(
				"Passenger's condition is COMPLETED, ArrivalStoryContainer floor is "
				+ "equal transportationTask end floor and validation should be ok :",
				passengerController.isArrivedPassengersVerified(), is(true));
	}

}
