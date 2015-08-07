package com.javacore.controllers;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.javacore.entities.Passenger;
import com.javacore.utils.CurrentWay;

public class TransportationTaskTest4 {

	@Test(timeout = 500)
	public void notifyElevatorPassengersGetOut() throws IOException {
		final int CURRENT_FLOOR = 3;
		final int END_FLOOR = 3;
		final int FLOOR_COUNT = 11;
		final CurrentWay CURRENT_WAY = CurrentWay.UP;

		TransportationTask mock = mock(TransportationTask.class);
		final boolean checkIsGetOutNotifyExpected = true;
		when(mock.checkIsGetOutNotify())
				.thenReturn(checkIsGetOutNotifyExpected);

		PassengerController passengerController = new PassengerController();
		passengerController.organizePassengers();
		/** Fill elevator container by 2 transportation tasks */
		Passenger passenger1 = new Passenger(FLOOR_COUNT);
		Passenger passenger2 = new Passenger(FLOOR_COUNT);
		passenger1.setStartFloor(2);
		passenger1.setEndFloor(END_FLOOR);
		passenger2.setStartFloor(2);
		passenger2.setEndFloor(END_FLOOR);
		TransportationTask tt1 = new TransportationTask(passenger1,
				passengerController);
		tt1.setContainer(passengerController.getElevatorCont());
		TransportationTask tt2 = new TransportationTask(passenger2,
				passengerController);
		tt2.setContainer(passengerController.getElevatorCont());
		tt1.startThread();
		tt2.startThread();
		passengerController.getElevatorCont().addTransportationTask(tt1);
		passengerController.getElevatorCont().addTransportationTask(tt2);

		List<TransportationTask> lst = passengerController.getElevatorCont()
				.getTransportationTaskLst();
		for (TransportationTask transportationTask : lst) {
			transportationTask.setCurrentFloor(CURRENT_FLOOR);
			transportationTask.setCurrWay(CURRENT_WAY);
			transportationTask.setGetOut(true);

			assertThat("Before running transportation task's thread "
					+ "his getOutFloorFlag must be equal 0:",
					transportationTask.getGetOutFloorFlag(), is(0));
			/** Run another thread */
			passengerController.proceedAnotherThread(transportationTask);

			boolean actual = mock.checkIsGetOutNotify();

			assertThat("Method checkIsGetOutNotify must to be launched :",
					actual, is(checkIsGetOutNotifyExpected));

			assertThat("After running transportation task's thread "
					+ "his getOutFloorFlag must be equal 3:",
					transportationTask.getGetOutFloorFlag(), is(END_FLOOR));
		}

	}

}
