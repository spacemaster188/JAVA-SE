package com.javacore.controllers;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;

import org.junit.Test;

import com.javacore.containers.DispatchStoryContainer;
import com.javacore.entities.Passenger;
import com.javacore.interfaces.Container;

public class TransportationTaskTest1 {
	private final int ZERO_MOVE_DELAY = 0;
	
	@Test
	public void createTransportationTaskAndCheckHisContainerChanging()
			throws InterruptedException, IOException {
		ElevatorController elevatorConroller = new ElevatorController();
		elevatorConroller.setMoveDelay(ZERO_MOVE_DELAY);
		PassengerController passengerController = elevatorConroller
				.getPassengersController();
		TransportationTask transportationTask = new TransportationTask(
				new Passenger(11), passengerController);

		assertThat("Container of transportation task must be null:",
				transportationTask.getContainer() == null, is(true));
		
		TransportationTask placedOnFloorTransportationTask = passengerController
				.getDispStorContLst().get(0).getTransportationTaskLst().get(0);
		Container placedOnFloorTransportationTaskContainer = placedOnFloorTransportationTask
				.getContainer();

		assertThat(
				"Container of placed on the floor transportation task must instance of DispatchStoryContainer:",
				placedOnFloorTransportationTaskContainer instanceof DispatchStoryContainer,
				is(true));
		
	}

}
