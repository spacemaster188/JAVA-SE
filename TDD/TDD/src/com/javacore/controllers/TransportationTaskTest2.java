package com.javacore.controllers;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.javacore.containers.DispatchStoryContainer;
import com.javacore.utils.PassengerConditions;

public class TransportationTaskTest2 {

	@Test
	public void setAllTransportationTasksAborted() throws IOException {
		final String ABORTED_PASSENGER_STATE = PassengerConditions.ABORTED
				.toString();
		final String IN_PROGRESS_PASSENGER_STATE = PassengerConditions.IN_PROGRESS
				.toString();
		PassengerController passengerController = new PassengerController();
		passengerController.organizePassengers();
		TransportationTask mock = mock(TransportationTask.class);
		final boolean checkIsAbortedStateExpected = true;
		when(mock.checkIsAbortedState())
				.thenReturn(checkIsAbortedStateExpected);

		List<DispatchStoryContainer> dispatchStoryContainerLst = passengerController
				.getDispStorContLst();

		for (DispatchStoryContainer cont : dispatchStoryContainerLst) {
			List<TransportationTask> lst = cont.getTransportationTaskLst();
			for (TransportationTask transportationTask : lst) {
				assertThat("Before terminating all transportation tasks"
						+ "Passenger's state must be IN_PROGRESS:",
						transportationTask.getPassenger()
								.getTransportationState().toString(),
						equalTo(IN_PROGRESS_PASSENGER_STATE));
			}
		}

		for (DispatchStoryContainer cont : dispatchStoryContainerLst) {
			List<TransportationTask> lst = cont.getTransportationTaskLst();
			for (TransportationTask transportationTask : lst) {
				transportationTask.setAborted();
				/** Run another thread */
				passengerController.proceedAnotherThread(transportationTask);

				boolean actual = mock.checkIsAbortedState();

				assertThat("Method checkIsAbortedState must to be launched :",
						actual, is(checkIsAbortedStateExpected));

				assertThat("After terminating all transportation tasks"
						+ " passenger's state must be changed to ABORTED:",
						transportationTask.getPassenger()
								.getTransportationState().toString(),
						equalTo(ABORTED_PASSENGER_STATE));
			}
		}
	}

}
