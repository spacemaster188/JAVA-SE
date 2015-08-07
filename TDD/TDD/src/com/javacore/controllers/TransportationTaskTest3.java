package com.javacore.controllers;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.javacore.containers.DispatchStoryContainer;
import com.javacore.entities.Passenger;
import com.javacore.utils.CurrentWay;

public class TransportationTaskTest3 {

	@Test (timeout = 500)
	public void notifyIncomingPassengersToEnter() throws IOException {
		final int CURRENT_FLOOR = 1;
		final int END_FLOOR = 8;
		final int FLOOR_COUNT = 11;
		final CurrentWay CURRENT_WAY = CurrentWay.UP;

		TransportationTask mock = mock(TransportationTask.class);
		final boolean checkIsEnterNotifyExpected = true;
		when(mock.checkIsEnterNotify()).thenReturn(checkIsEnterNotifyExpected);

		PassengerController passengerController = new PassengerController();
		/**
		 * Create DispatchStoryContainer with 2 transportation tasks which start
		 * from 1-st floor
		 */
		DispatchStoryContainer container = new DispatchStoryContainer(
				CURRENT_FLOOR);
		Passenger passenger1 = new Passenger(FLOOR_COUNT);
		Passenger passenger2 = new Passenger(FLOOR_COUNT);
		passenger1.setStartFloor(CURRENT_FLOOR);
		passenger1.setEndFloor(END_FLOOR);
		passenger2.setStartFloor(CURRENT_FLOOR);
		passenger2.setEndFloor(END_FLOOR);
		TransportationTask tt1 = new TransportationTask(passenger1,
				passengerController);
		tt1.setContainer(container);
		TransportationTask tt2 = new TransportationTask(passenger2,
				passengerController);
		tt2.setContainer(container);
		tt1.startThread();
		tt2.startThread();
		container.addTransportationTask(tt1);
		container.addTransportationTask(tt2);
		passengerController.getDispStorContLst().add(container);

		/** Run notifyIncomingPassengersToEnter method from passengerController */
		for (DispatchStoryContainer dispatchStoryContainer : passengerController
				.getDispStorContLst()) {
			if (dispatchStoryContainer.getFloor() == CURRENT_FLOOR) {
				List<TransportationTask> transpTaskLst = dispatchStoryContainer
						.getTransportationTaskLst();
				for (TransportationTask transportationTask : transpTaskLst) {
					transportationTask.setCurrentFloor(CURRENT_FLOOR);
					transportationTask.setCurrWay(CURRENT_WAY);
					transportationTask.setEnter(true);

					assertThat(
							"Before running transportation task's thread "
							+ "his onboardFloorFlag must be equal 0:",
							transportationTask.getOnboardFloorFlag(),
							is(0));
					/** Run another thread */
					passengerController
							.proceedAnotherThread(transportationTask);

					boolean actual = mock.checkIsEnterNotify();

					assertThat(
							"Method checkIsEnterNotify must to be launched :",
							actual, is(checkIsEnterNotifyExpected));
					
					assertThat(
							"After running transportation task's thread "
							+ "his onboardFloorFlag must be equal 1:",
							transportationTask.getOnboardFloorFlag(),
							is(CURRENT_FLOOR));
				}
			}
		}

	}

}
