package com.javacore.controllers;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.javacore.containers.ArrivalStoryContainer;
import com.javacore.entities.Passenger;

public class PassengerControllerTest7 {

	@Test
	public void fillArrivalStoryContainerByPassengerAndCheckisAllPassengersArrivedForFinalServiceChecking()
			throws IOException {
		final int FLOOR = 11;
		PassengerController passengerController = new PassengerController();
		ArrivalStoryContainer container = new ArrivalStoryContainer(FLOOR);
		List<ArrivalStoryContainer> lst = new ArrayList<ArrivalStoryContainer>();
		lst.add(container);
		passengerController.setArrivStorContLst(lst);
		passengerController.organizePassengers();                   // change passengers count to 4

		assertThat(
				"Before filling by passengers the arrival story container validation"
				+ " should be fail cause quantity of arrived passengers is not equal overall "
				+ "passengers quantity  :",
				passengerController.isAllPassengersArrived(), is(false));

		Passenger passenger1 = new Passenger(FLOOR);
		TransportationTask transportationTask = new TransportationTask(
				passenger1, passengerController);
		ArrivalStoryContainer arrivalContainer = passengerController.getArrivStorContLst().get(0);
		arrivalContainer.addTransportationTask(transportationTask); //add 4 transportation tasks
		arrivalContainer.addTransportationTask(transportationTask);
		arrivalContainer.addTransportationTask(transportationTask);
		arrivalContainer.addTransportationTask(transportationTask);

		assertThat(
				"After filling by passengers the arrival story container validation"
				+ " should be ok :",
				passengerController.isAllPassengersArrived(), is(true));

	}

}
