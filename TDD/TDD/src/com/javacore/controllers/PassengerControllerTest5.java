package com.javacore.controllers;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;

import org.junit.Test;

import com.javacore.entities.Passenger;

public class PassengerControllerTest5 {

	@Test
	public void fillElevatorContainerByPassengersAndCheckIsElevatorContainersEmptyForFinalServiceChecking()
			throws IOException {
		final int FLOOR = 11;
		PassengerController passengerController = new PassengerController();
		passengerController.organizePassengers();
		
		assertThat(
				"After arrive all passengers to the end point validation should be ok :",
				passengerController.isElevatorContainersEmpty(), is(true));
		
		Passenger passenger1 = new Passenger(FLOOR);
		TransportationTask transportationTask1 = new TransportationTask(
				passenger1, passengerController);
		passengerController.getElevatorCont().getTransportationTaskLst()
				.add(transportationTask1);

		assertThat(
				"After filling of any container by passenger validation should fail :",
				passengerController.isElevatorContainersEmpty(), is(false));

	}

}
