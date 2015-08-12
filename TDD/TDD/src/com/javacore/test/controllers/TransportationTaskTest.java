package com.javacore.test.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.javacore.containers.DispatchStoryContainer;
import com.javacore.controllers.ElevatorController;
import com.javacore.controllers.PassengerController;
import com.javacore.controllers.TransportationTask;
import com.javacore.entities.Passenger;
import com.javacore.interfaces.Container;
import com.javacore.utils.CurrentWay;
import com.javacore.utils.PassengerConditions;

public class TransportationTaskTest {
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
	
	@Test
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
	
	@Test
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
