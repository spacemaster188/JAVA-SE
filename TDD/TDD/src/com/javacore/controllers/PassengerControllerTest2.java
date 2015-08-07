package com.javacore.controllers;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;

import org.junit.Test;

import com.javacore.entities.Passenger;

public class PassengerControllerTest2 {
	private PassengerController passengerController;

	@Test
	public void fillElevatorContainerByPassengerAndCheckIsElevatorVacant() throws IOException {
		this.passengerController = new PassengerController();
		passengerController.organizePassengers();
		assertThat("Elevator capacity must be more than 1 :",
				passengerController.getElevatorCont()
								.getElevatorCapacity() > 1, is(true));
		passengerController.getElevatorCont().addTransportationTask(
				new TransportationTask(new Passenger(11), passengerController));
		assertThat("Elevator container must be vacant :",
				passengerController.isElevatorVacant(), is(true));
	}

	public boolean isElevatorContainerNotEmpty() {
		if (passengerController.getElevatorCont().getTransportationTaskLst()
				.size() > 0) {
			return true;
		}
		return false;
	}

}
