package com.javacore.controllers;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.javacore.containers.DispatchStoryContainer;
import com.javacore.entities.Passenger;

public class PassengerControllerTest6 {

	@Test
	public void fillDispatchStoryContainerByPassengersAndCheckisAllDispatchStoryContainersEmptyForFinalServiceChecking()
			throws IOException {
		final int FLOOR = 11;
		PassengerController passengerController = new PassengerController();
		DispatchStoryContainer container = new DispatchStoryContainer(FLOOR);
		List <DispatchStoryContainer> lst = new ArrayList<DispatchStoryContainer>();
		lst.add(container);
		passengerController.setDispStorContLst(lst);
		
		assertThat(
				"Before filling by passenger the dispatchStory container validation should be ok :",
				passengerController.isAllDispatchStoryContainersEmpty(), is(true));
		
		Passenger passenger1 = new Passenger(FLOOR);
		TransportationTask transportationTask1 = new TransportationTask(
				passenger1, passengerController);
		passengerController.getDispStorContLst().get(0).addTransportationTask(transportationTask1);

		assertThat(
				"After filling  by passenger the dispatchStory container validation should fail :",
				passengerController.isAllDispatchStoryContainersEmpty(), is(false));

	}

}
